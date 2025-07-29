package com.example.nero_clothing_backend.model.dto.Product;

import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import static com.example.nero_clothing_backend.common.RegexConstants.*;

@Data
@Builder
public class ProductRequestDto {

    @NotNull
    @Pattern(regexp = PRODUCT_NAME, message = "Name can only contain letters, numbers, and spaces")
    private String name;

    @NotNull
    @Pattern(regexp = PRODUCT_DESCRIPTION, message = "Description can only contain letters, numbers, and spaces")
    private String description;

    @NotNull
    @Pattern(regexp = PRICE, message = "Price must be a valid number with up to two decimal places")
    private String price;

    @NotNull
//    @Pattern(regexp = CATEGORY, message = "Price must be a valid number with up to two decimal places")
    private CategoryEnum category;
}
