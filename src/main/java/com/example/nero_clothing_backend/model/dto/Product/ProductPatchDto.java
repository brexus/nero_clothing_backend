package com.example.nero_clothing_backend.model.dto.Product;

import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import static com.example.nero_clothing_backend.common.RegexConstants.CATEGORY;

@Data
@Builder
public class ProductPatchDto {

    @Pattern(regexp = "^[a-zA-Z0-9\\s]{3,50}$|^$", message = "Name can only contain letters, numbers, and spaces")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9\\s]{3,50}$|^$", message = "Description can only contain letters, numbers, and spaces")
    private String description;

    @Pattern(regexp = "^(\\d+\\.\\d{1,2}|\\d+)$|^$", message = "Price must be a valid number with up to two decimal places")
    private String price;

    @Pattern(regexp = CATEGORY, message = "zła kategoria")
    private CategoryEnum category;
}
