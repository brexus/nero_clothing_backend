package com.example.nero_clothing_backend.service.CustomerOrder;

import com.example.nero_clothing_backend.exception.InsufficientStockException;
import com.example.nero_clothing_backend.exception.OrderNotFoundException;
import com.example.nero_clothing_backend.mapper.CustomerOrderMapper;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderRequestDto;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderResponseDto;
import com.example.nero_clothing_backend.model.entity.CustomerOrder;
import com.example.nero_clothing_backend.model.entity.OrderAddress;
import com.example.nero_clothing_backend.model.entity.OrderItem;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
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

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Override
    @Transactional
    public CustomerOrderResponseDto createCustomerOrder(CustomerOrderRequestDto reqDto) {
        // (1). Create empty order
        CustomerOrder newOrder = new CustomerOrder();

        // (2) set shipping address
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

        // (3) check stock availability and create order items

        List<OrderItem> orderItemList = new ArrayList<OrderItem>();

        for (var item : reqDto.getItems()) {
            ProductVariant pv = productVariantRepository.findById(item.getProductVariantId()).orElseThrow();

            if (hasSufficientStock(pv, item.getQuantity())) {
                // (A) if stock is enough

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
                // (B) if stock is not enough
                throw new InsufficientStockException(pv.getId(), pv.getStockQuantity(), item.getQuantity());
            }
        }

        newOrder.setItems(orderItemList);

        // (4) Set CUSTOMER (check if customer with given email already exists)

//        Customer customer = customerRepository.findByGuestEmail(reqDto.getEmail());


        // (a) customer nie istnieje - nigdy nie zamawial - tworzymy nowego guest'a
        // (b) user istnieje ale nie ma przypisanego customer'a
        // (c) user istnieje i ma przypisanego customer'a

        // sprawdzić jak w springu się sprawdza kto jest zalogowany

        String email = authentication.getName();


//        if(customer == null) {
//            customer = Customer.builder()
//                    .guestEmail(reqDto.getEmail())
//                    .build();
//
//            customerRepository.save(customer);
//        }


//        User user = userRepository.getByEmail(reqDto.getEmail());


//        if(user != null) {
//            customer.setUser(user);
//
//        }
//
//
//        if (customer == null) {
//            customer = Customer.builder()
//                    .guestEmail(reqDto.getEmail())
//                    .build();
//
//            customerRepository.save(customer);
//        }


//        newOrder.setCustomer(customer);
        newOrder.setFirstName(reqDto.getFirstName());
        newOrder.setLastName(reqDto.getLastName());
        newOrder.setPhoneNumber(reqDto.getPhoneNumber());

        // (4) Set USER if userId is provided - future

        // (5) status -> "NEW"
        newOrder.setStatus(OrderStatus.NEW);

        // (6) calculate total amount

        Long totalAmount = newOrder.getItems().stream().mapToLong(OrderItem::getTotalPrice).sum();
        newOrder.setTotalAmount(totalAmount);

        // (7) set shippingDate and deliveryDate to null for now
        newOrder.setShippingDate(null);
        newOrder.setDeliveryDate(null);

        // (8) save to DB
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
