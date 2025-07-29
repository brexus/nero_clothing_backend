package com.example.nero_clothing_backend.model.dto.OrderItem;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemRequestDto {
    private Integer quantity;
    private Long productVariantId;
}
