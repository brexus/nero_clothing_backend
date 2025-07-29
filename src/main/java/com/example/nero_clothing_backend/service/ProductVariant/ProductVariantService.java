package com.example.nero_clothing_backend.service.ProductVariant;

import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;

public interface ProductVariantService {
    ProductVariantResponseDto createProductVariant(ProductVariantRequestDto requestDto);
}
