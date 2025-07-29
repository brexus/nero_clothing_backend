package com.example.nero_clothing_backend.service.ProductImage;

import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageResponseDto;
import com.example.nero_clothing_backend.mapper.ProductImageMapper;
import com.example.nero_clothing_backend.model.entity.ProductImage;
import com.example.nero_clothing_backend.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public ProductImageResponseDto createProductImage(ProductImageRequestDto reqDto) {

        ProductImage productImage = ProductImage.builder()
                .imageUrl(reqDto.getImageUrl())
                .altText(reqDto.getAltText())
//                .product(null) // Product will be set later if needed
                .build();

//        Product product = productRepository.findById(productImage.getId())
//                .orElseThrow(() -> new RuntimeException("Product not found with id: " + product.getId()));

//        productImage.setProduct(product);
        ProductImage savedImage = productImageRepository.save(productImage);

        return ProductImageMapper.toDto(savedImage);
    }

    @Override
    public ProductImageResponseDto getProductImageById(Long id) {
        ProductImage prodImg = productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product image not found with id: " + id));

        return ProductImageMapper.toDto(prodImg);
    }

    @Override
    public List<ProductImageResponseDto> getAllProductImages() {

        List<ProductImageResponseDto> imgList = productImageRepository.findAll().stream()
                .map(ProductImageMapper::toDto)
                .toList();

        return imgList;
    }

    @Override
    public ProductImageResponseDto updatePartialProductImage(Long id, ProductImageRequestDto reqDto) {
         ProductImage productImage = productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product image not found with id: " + id));

         if(reqDto.getImageUrl() != null) {
             productImage.setImageUrl(reqDto.getImageUrl());
         }
        if(reqDto.getAltText() != null) {
            productImage.setAltText(reqDto.getAltText());
        }

        ProductImage savedImage = productImageRepository.save(productImage);

        return ProductImageMapper.toDto(savedImage);
    }

    @Override
    public void deleteProductImage(Long id) {
        productImageRepository.deleteById(id);
    }
}
