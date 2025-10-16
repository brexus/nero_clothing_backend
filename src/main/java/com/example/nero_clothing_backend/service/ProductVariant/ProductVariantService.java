package com.example.nero_clothing_backend.service.ProductVariant;

import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantPatchDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;

import java.util.List;
import java.util.Map;

public interface ProductVariantService {
    ProductVariantResponseDto createProductVariant(ProductVariantRequestDto requestDto);

    List<ProductVariantResponseDto> getAllProductVariants();

    ProductVariantResponseDto getProductVariantById(Long id);

    ProductVariantResponseDto updatePartialProductVariant(Long id, ProductVariantPatchDto requestDto);

    void deleteProductVariant(Long id);

    Map<String, String> getBasicInfoOfProductVariantById(Long id);
}
