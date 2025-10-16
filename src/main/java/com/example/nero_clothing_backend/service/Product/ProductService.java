package com.example.nero_clothing_backend.service.Product;

import com.example.nero_clothing_backend.model.dto.Product.ProductPageInfoDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto reqDto);

    ProductResponseDto getProduct(Long id);

    Page<ProductResponseDto> getAllProducts(String category, int page, int size);

    void deleteProduct(Long id);

    ProductResponseDto updatePartialProduct(Long id, ProductPatchDto reqDto);

    Map<String, Integer> getProductVariantQuantitiesByProductId(Long id);

    ProductVariantResponseDto getProductVariantByProductIdAndSize(Long productId, ProductSizeEnum size);

    ProductPageInfoDto getProductPageInfo(Long id);

}
