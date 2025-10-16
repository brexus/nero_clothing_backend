package com.example.nero_clothing_backend.model.dto.Product;

import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String name;
    private String description;
    private Long price;
    private CategoryEnum category;

}
