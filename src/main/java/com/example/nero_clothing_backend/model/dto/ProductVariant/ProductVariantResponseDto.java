package com.example.nero_clothing_backend.model.dto.ProductVariant;

import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ProductVariantResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProductSizeEnum size;
    private Integer stockQuantity;
    private Long productId;

}
