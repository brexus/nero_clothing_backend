package com.example.nero_clothing_backend.service.Order;

import com.example.nero_clothing_backend.model.dto.Order.OrderRequestDto;
import com.example.nero_clothing_backend.model.dto.Order.OrderResponseDto;
import com.example.nero_clothing_backend.model.entity.ProductVariant;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto reqDto);

    OrderResponseDto updatePartialOrder(OrderRequestDto reqDto, Long id);

    Boolean hasSufficientStock(ProductVariant productVariant, Integer requestQuantity);
}
