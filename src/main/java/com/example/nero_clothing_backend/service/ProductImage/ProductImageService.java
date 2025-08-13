package com.example.nero_clothing_backend.service.ProductImage;


import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageResponseDto;

import java.util.List;

public interface ProductImageService {

    ProductImageResponseDto createProductImage(ProductImageRequestDto reqDto);
    List<ProductImageResponseDto> getProductImagesByProductId(Long productVariantId);
    List<ProductImageResponseDto> getAllProductImages();
    ProductImageResponseDto updatePartialProductImage(Long id, ProductImageRequestDto reqDto);
    void deleteProductImage(Long id);

}
