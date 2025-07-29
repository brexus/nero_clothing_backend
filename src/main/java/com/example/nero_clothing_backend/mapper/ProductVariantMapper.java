package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import lombok.Data;


public class ProductVariantMapper {
    public static ProductVariant toEntity(ProductVariantRequestDto reqDto) {
        if (reqDto == null) {
            return null;
        }

        ProductVariant variant = ProductVariant.builder()
                .size(reqDto.getSize())
                .stockQuantity(reqDto.getStockQuantity())
                .product(null)
                .build();

        return variant;
    }

    public static ProductVariantResponseDto toDto(ProductVariant variant) {
        if (variant == null) {
            return null;
        }

        ProductVariantResponseDto resDto = ProductVariantResponseDto.builder()
                .id(variant.getId())
                .createdAt(variant.getCreatedAt())
                .updatedAt(variant.getUpdatedAt())
                .size(variant.getSize())
                .stockQuantity(variant.getStockQuantity())
                .productId(variant.getProduct().getId())
                .build();

        return resDto;
    }
}

