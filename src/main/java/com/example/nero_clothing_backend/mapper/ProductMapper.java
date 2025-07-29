package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.model.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequestDto reqDto) {
        if (reqDto == null) {
            return null;
        }

        Product product = Product.builder()
                .name(reqDto.getName())
                .description(reqDto.getDescription())
                .price(reqDto.getPrice() != null ? Double.parseDouble(reqDto.getPrice()) : null)
                .category(reqDto.getCategory())
                .build();

        return product;
    }

    public static ProductResponseDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

}
