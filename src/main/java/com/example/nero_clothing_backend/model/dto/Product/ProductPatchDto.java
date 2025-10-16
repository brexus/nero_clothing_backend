package com.example.nero_clothing_backend.model.dto.Product;

import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import static com.example.nero_clothing_backend.common.RegexConstants.PRODUCT_DESCRIPTION;
import static com.example.nero_clothing_backend.common.RegexConstants.PRODUCT_NAME;

@Data
@Builder
public class ProductPatchDto {

    @Pattern(regexp = PRODUCT_NAME, message = "Name can only contain letters, numbers, and spaces")
    private String name;

    @Pattern(regexp = PRODUCT_DESCRIPTION, message = "Description can only contain letters, numbers, and spaces")
    private String description;

    @Positive(message = "Price must be a positive number.")
    private Long price;

    private CategoryEnum category;
}
