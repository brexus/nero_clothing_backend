package com.example.nero_clothing_backend.service.Product;

import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;

import java.util.List;


public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto reqDto);
    ProductResponseDto getProduct(Long id);
    List<ProductResponseDto> getAllProducts();
    void deleteProduct(Long id);
    ProductResponseDto updatePartialProduct(Long id, ProductPatchDto reqDto);
}
