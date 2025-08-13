package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageResponseDto;
import com.example.nero_clothing_backend.model.entity.ProductImage;

public class ProductImageMapper {

    public static ProductImage toEntity(ProductImageRequestDto imgDto) {

        if (imgDto == null) {
            return null;
        }

        ProductImage prodImg = new ProductImage();
        prodImg.setImageUrl(imgDto.getImageUrl());
        prodImg.setAltText(imgDto.getAltText());
        prodImg.setProduct(null); // Assuming product variant is set later

        return prodImg;
    }

    public static ProductImageResponseDto toDto(ProductImage imgDto) {

        if (imgDto == null) {
            return null;
        }

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
