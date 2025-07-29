package com.example.nero_clothing_backend.model.dto.OrderItem;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemResponseDto {
    private Long orderId;
    private Integer quantity;
    private Long productVariantId;
    private Double totalPrice;
}
