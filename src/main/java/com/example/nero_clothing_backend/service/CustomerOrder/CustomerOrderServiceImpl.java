package com.example.nero_clothing_backend.service.CustomerOrder;

import com.example.nero_clothing_backend.exception.InsufficientStockException;
import com.example.nero_clothing_backend.exception.OrderNotFoundException;
import com.example.nero_clothing_backend.mapper.CustomerOrderMapper;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderRequestDto;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderResponseDto;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomerOrder newOrder = new CustomerOrder();

        OrderAddress newShippingAddress = OrderAddress.builder()
                .street(reqDto.getShippingAddress().getStreet())
                .city(reqDto.getShippingAddress().getCity())
                .apartment(reqDto.getShippingAddress().getApartment())
                .zipCode(reqDto.getShippingAddress().getZipCode())
                .country(reqDto.getShippingAddress().getCountry())
                .building(reqDto.getShippingAddress().getBuilding())
                .build();

        orderAddressRepository.save(newShippingAddress);
        newOrder.setShippingAddress(newShippingAddress);


        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        for (var item : reqDto.getItems()) {
            ProductVariant pv = productVariantRepository.findById(item.getProductVariantId()).orElseThrow();

            if (hasSufficientStock(pv, item.getQuantity())) {
                OrderItem oi = OrderItem.builder()
                        .customerOrder(newOrder)
                        .productVariant(pv)
                        .unitPrice(pv.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .totalPrice(pv.getProduct().getPrice() * item.getQuantity())
                        .build();

                orderItemList.add(oi);
                pv.setStockQuantity(pv.getStockQuantity() - item.getQuantity());
                productVariantRepository.save(pv);
            } else {
                throw new InsufficientStockException(pv.getId(), pv.getStockQuantity(), item.getQuantity());
            }
        }

        newOrder.setItems(orderItemList);


        Customer customer = null;

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            String userEmail = authentication.getName();
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found for email: " + userEmail));

            customer = customerRepository.findByUserId(user.getId())
                    .orElseGet(() -> {
                        Customer newCustomer = Customer.builder()
                                .user(user)
                                .build();
                        return customerRepository.save(newCustomer);
                    });

        } else {
            customer = customerRepository.findByGuestEmail(reqDto.getEmail());

            if (customer == null) {
                customer = Customer.builder()
                        .guestEmail(reqDto.getEmail())
                        .build();

                customerRepository.save(customer);
            }
        }
        newOrder.setCustomer(customer);

        newOrder.setFirstName(reqDto.getFirstName());
        newOrder.setLastName(reqDto.getLastName());
        newOrder.setPhoneNumber(reqDto.getPhoneNumber());


        newOrder.setStatus(OrderStatus.NEW);
        newOrder.setPaymentMethod(reqDto.getPaymentMethod());

        Long totalAmount = newOrder.getItems().stream().mapToLong(OrderItem::getTotalPrice).sum();
        newOrder.setTotalAmount(totalAmount);


        newOrder.setShippingDate(null);
        newOrder.setDeliveryDate(null);



        CustomerOrder savedOrder = orderRepository.save(newOrder);

        return CustomerOrderMapper.toDto(savedOrder);
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
}
