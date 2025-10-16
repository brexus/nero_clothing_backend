package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.entity.ProductVariant;

public class ProductVariantMapper {
    public static ProductVariant toEntity(ProductVariantRequestDto reqDto) {

        return ProductVariant.builder()
                .size(reqDto.getSize())
                .stockQuantity(reqDto.getStockQuantity())
                .product(null)
                .build();
    }

    public static ProductVariantResponseDto toDto(ProductVariant variant) {

        return ProductVariantResponseDto.builder()
                .id(variant.getId())
                .createdAt(variant.getCreatedAt())
                .updatedAt(variant.getUpdatedAt())
                .size(variant.getSize())
                .stockQuantity(variant.getStockQuantity())
                .productId(variant.getProduct().getId())
                .build();
    }
}

