package com.example.nero_clothing_backend.model.dto.ProductVariant;

import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ProductVariantRequestDto {
    @NotBlank(message = "Product size is required.")
//    @Pattern(regexp = PRODUCT_VARIANT_SIZE, message = "Size must be one of: [XS, S, M, L, XL].")
    private String size;

    @NotNull(message = "Stock quantity is required.")
    @Positive(message = "Stock quantity must be a positive integer.")
    private Integer stockQuantity;

    @NotNull(message = "Product id is required.")
    @Positive(message = "Product id must be a positive number.")
    private Long productId;

}
