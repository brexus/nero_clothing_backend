package com.example.nero_clothing_backend.service.CustomerOrder;

import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderRequestDto;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderResponseDto;
import com.example.nero_clothing_backend.model.entity.ProductVariant;

public interface CustomerOrderService {
    CustomerOrderResponseDto createCustomerOrder(CustomerOrderRequestDto reqDto);

    CustomerOrderResponseDto updatePartialCustomerOrder(CustomerOrderRequestDto reqDto, Long id);

    Boolean hasSufficientStock(ProductVariant productVariant, Integer requestQuantity);
}
