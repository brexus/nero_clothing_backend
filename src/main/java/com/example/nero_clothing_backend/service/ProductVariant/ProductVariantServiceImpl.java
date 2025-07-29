package com.example.nero_clothing_backend.service.ProductVariant;

import com.example.nero_clothing_backend.exception.ProductNotFoundException;
import com.example.nero_clothing_backend.mapper.ProductVariantMapper;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import com.example.nero_clothing_backend.repository.ProductRepository;
import com.example.nero_clothing_backend.repository.ProductVariantRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductVariantResponseDto createProductVariant(ProductVariantRequestDto reqDto) {

        ProductVariant productVariant = ProductVariantMapper.toEntity(reqDto);

        Product product = productRepository.findById(reqDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(reqDto.getProductId()));
        productVariant.setProduct(product);

        ProductVariant savedProductVariant = productVariantRepository.save(productVariant);

        return ProductVariantMapper.toDto(savedProductVariant);
    }
}
