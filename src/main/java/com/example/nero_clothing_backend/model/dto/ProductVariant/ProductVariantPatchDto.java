package com.example.nero_clothing_backend.model.dto.ProductVariant;

import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductVariantPatchDto {
    private ProductSizeEnum size;

    @Positive(message = "Stock quantity must be a positive integer.")
    private Integer stockQuantity;

    @Positive(message = "Product id must be a positive number.")
    private Long productId;

}
