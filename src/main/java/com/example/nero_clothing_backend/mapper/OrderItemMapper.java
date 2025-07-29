package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemResponseDto;
import com.example.nero_clothing_backend.model.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItem toEntity(OrderItemRequestDto reqDto) {

        OrderItem orderItem = OrderItem.builder()
                .quantity(reqDto.getQuantity())
                .productVariant(null)
                .totalPrice(null)
                .order(null)
                .build();

        return orderItem;
    }

    public static OrderItemResponseDto toDto(OrderItem orderItem) {

        return OrderItemResponseDto.builder()
                .orderId(orderItem.getOrder().getId())
                .quantity(orderItem.getQuantity())
                .productVariantId(orderItem.getProductVariant().getId())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }
}
