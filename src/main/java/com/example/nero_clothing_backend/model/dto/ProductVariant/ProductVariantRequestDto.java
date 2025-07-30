package com.example.nero_clothing_backend.model.dto.ProductVariant;

import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

//import static com.example.nero_clothing_backend.common.RegexConstants.PRODUCT_SIZE;
import static com.example.nero_clothing_backend.common.RegexConstants.STOCK_QUANTITY;

@Data
@Builder
public class ProductVariantRequestDto {
    @NotNull
//    @Pattern(regexp = PRODUCT_SIZE, message = "zły size")
    private ProductSizeEnum size;

    private Integer stockQuantity;

    @NotNull
    private Long productId;

}
