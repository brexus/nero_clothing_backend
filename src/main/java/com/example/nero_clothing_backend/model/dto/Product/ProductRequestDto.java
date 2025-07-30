package com.example.nero_clothing_backend.model.dto.Product;

import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import static com.example.nero_clothing_backend.common.RegexConstants.*;

@Data
@Builder
public class ProductRequestDto {

    @NotBlank(message = "Product name is required.")
    @Pattern(regexp = PRODUCT_NAME, message = "Name can only contain letters, numbers, and spaces")
    private String name;

    @NotBlank(message = "Product description is required.")
    @Pattern(regexp = PRODUCT_DESCRIPTION, message = "Description can only contain letters, numbers, and spaces")
    private String description;

    @NotBlank(message = "Price is required.")
    @Pattern(regexp = PRICE, message = "Price must be a valid number with up to two decimal places")
    private String price;

    @NotBlank(message = "Product category description is required.")
    @Pattern(regexp = PRODUCT_CATEGORY, message = "Invalid category. Must be one of: [TSHIRTS, HOODIES, BOTTOMS, ACCESSORIES]")
    private String category;

}
