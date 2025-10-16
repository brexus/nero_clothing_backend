package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageResponseDto;
import com.example.nero_clothing_backend.model.entity.ProductImage;

public class ProductImageMapper {

    public static ProductImage toEntity(ProductImageRequestDto imgDto) {

        return ProductImage.builder()
                .imageUrl(imgDto.getImageUrl())
                .altText(imgDto.getAltText())
                .product(null)
                .build();
    }

    public static ProductImageResponseDto toDto(ProductImage imgDto) {

        return ProductImageResponseDto.builder()
                .id(imgDto.getId())
                .imageUrl(imgDto.getImageUrl())
                .altText(imgDto.getAltText())
                .productId(imgDto.getProduct() != null ? imgDto.getProduct().getId() : null)
                .createdAt(imgDto.getCreatedAt())
                .updatedAt(imgDto.getUpdatedAt())
                .build();
    }
}
