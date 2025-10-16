package com.example.nero_clothing_backend.model.dto.Product;

import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageResponseDto;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ProductPageInfoDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private CategoryEnum category;
    private Map<String, Integer> variantQuantities;
    private List<ProductImageResponseDto> productImages;
}
