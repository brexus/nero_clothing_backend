package com.example.nero_clothing_backend.model.dto.Order;

import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemResponseDto;
import com.example.nero_clothing_backend.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Double totalAmount;
    private OrderStatus status;
    private String paymentMethod;
    private LocalDateTime shippingDate;
    private LocalDateTime deliveryDate;
    private Long customerId;


    private String deliveryStreet;
    private String deliveryBuilding;
    private String deliveryApartment;
    private String deliveryZipCode;
    private String deliveryCity;
    private String deliveryCountry;

    private List<OrderItemResponseDto> items;
}
