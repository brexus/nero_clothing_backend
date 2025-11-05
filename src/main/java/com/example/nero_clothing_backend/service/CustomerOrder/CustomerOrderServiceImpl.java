package com.example.nero_clothing_backend.service.CustomerOrder;

import com.example.nero_clothing_backend.exception.InsufficientStockException;
import com.example.nero_clothing_backend.exception.OrderNotFoundException;
import com.example.nero_clothing_backend.mapper.CustomerOrderMapper;
import com.example.nero_clothing_backend.mapper.OrderAddressMapper;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderRequestDto;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderResponseDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemRequestDto;
import com.example.nero_clothing_backend.model.entity.*;
import com.example.nero_clothing_backend.model.enums.OrderStatus;
import com.example.nero_clothing_backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {
    private final CustomerOrderRepository orderRepository;
    private final OrderAddressRepository orderAddressRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final ProductVariantRepository productVariantRepository;


    @Override
    @Transactional
    public CustomerOrderResponseDto createCustomerOrder(CustomerOrderRequestDto reqDto) {
        OrderAddress newShippingAddress = OrderAddressMapper.toEntity(reqDto.getShippingAddress());
        orderAddressRepository.save(newShippingAddress);

        List<Long> itemsIds = reqDto.getItems().stream()
                .map(OrderItemRequestDto::getProductVariantId)
                .toList();

        Map<Long, ProductVariant> productVariantsMap = productVariantRepository.findAllByIdIn(itemsIds)
                .stream()
                .collect(Collectors.toMap(ProductVariant::getId, pv -> pv));

        Customer customer = getOrCreateCustomer(reqDto.getEmail());

        CustomerOrder newOrder = CustomerOrder.builder()
                .customer(customer)
                .firstName(reqDto.getFirstName())
                .lastName(reqDto.getLastName())
                .phoneNumber(reqDto.getPhoneNumber())
                .shippingAddress(newShippingAddress)
                .paymentMethod(reqDto.getPaymentMethod())
                .status(OrderStatus.NEW)
                .shippingDate(null)
                .deliveryDate(null)
                .items(new ArrayList<>())
                .totalAmount(0L)
                .build();

        List<OrderItem> orderItemList = reqDto.getItems().stream()
                .map(item -> verifyStockAndAddToOrderItemList(productVariantsMap, item, newOrder))
                .toList();

        newOrder.setItems(orderItemList);
        newOrder.setTotalAmount(orderItemList.stream()
                .mapToLong(OrderItem::getTotalPrice)
                .sum());

        return CustomerOrderMapper.toDto(orderRepository.save(newOrder));
    }


    @Override
    @Deprecated(forRemoval = true)
    public CustomerOrderResponseDto updatePartialCustomerOrder(CustomerOrderRequestDto reqDto, Long id) {
        CustomerOrder order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        return null;
    }

    @Override
    public Boolean hasSufficientStock(ProductVariant productVariant, Integer requestQuantity) {
        return productVariant.getStockQuantity() >= requestQuantity;
    }

    private Customer getOrCreateCustomer(String guestEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isAuthenticated(authentication)) {
            String userEmail = authentication.getName();
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found for email: " + userEmail));

            return customerRepository.findByUserId(user.getId())
                    .orElseGet(() -> {
                        Customer newCustomer = Customer.builder()
                                .user(user)
                                .build();
                        return customerRepository.save(newCustomer);
                    });
        } else {
            return customerRepository.findByGuestEmail(guestEmail)
                    .orElseGet(() -> {
                        Customer newCustomer = Customer.builder()
                                .guestEmail(guestEmail)
                                .build();
                        return customerRepository.save(newCustomer);
                    });
        }
    }

    private OrderItem verifyStockAndAddToOrderItemList(Map<Long, ProductVariant> productVariantsMap,
                                                       OrderItemRequestDto item,
                                                       CustomerOrder newOrder) {
        ProductVariant pv = productVariantsMap.get(item.getProductVariantId());

        if (!hasSufficientStock(pv, item.getQuantity())) {
            throw new InsufficientStockException(pv.getId(), pv.getStockQuantity(), item.getQuantity());
        }

        OrderItem oi = OrderItem.builder()
                .customerOrder(newOrder)
                .productVariant(pv)
                .unitPrice(pv.getProduct().getPrice())
                .quantity(item.getQuantity())
                .totalPrice(pv.getProduct().getPrice() * item.getQuantity())
                .build();
        pv.setStockQuantity(pv.getStockQuantity() - item.getQuantity());
        productVariantRepository.save(pv);
        return oi;
    }

    private boolean isAuthenticated(Authentication authentication) {
        return (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName()));
    }
}
