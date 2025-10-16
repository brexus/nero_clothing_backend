package com.example.nero_clothing_backend.model.dto.CustomerOrder;

import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressResponseDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemResponseDto;
import com.example.nero_clothing_backend.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String paymentMethod;
    private Long customerId;

    private OrderAddressResponseDto deliveryAddress;

    private Long totalAmount;
    private OrderStatus status;
    private LocalDateTime shippingDate;
    private LocalDateTime deliveryDate;

    private List<OrderItemResponseDto> items;
}
