package com.example.nero_clothing_backend.model.dto.Product;

import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProductResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String name;
    private String description;
    private Double price;
    private CategoryEnum category;

}
