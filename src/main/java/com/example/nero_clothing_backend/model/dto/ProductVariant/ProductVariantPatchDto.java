package com.example.nero_clothing_backend.model.dto.ProductVariant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ProductVariantPatchDto {
//    @Pattern(regexp = , message = "Size must be one of: [XS, S, M, L, XL].")
    private String size;

    @Positive(message = "Stock quantity must be a positive integer.")
    private Integer stockQuantity;

    @Positive(message = "Product id must be a positive number.")
    private Long productId;

}
