package com.example.nero_clothing_backend.service.Product;

import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.exception.ProductNotFoundException;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto reqDto) {

        Product product = Product.builder()
                .name(reqDto.getName())
                .description(reqDto.getDescription())
                .price(reqDto.getPrice() != null ? Double.parseDouble(reqDto.getPrice()) : null)
                .category(reqDto.getCategory())
                .build();

        Product savedProduct = productRepository.save(product);

        return ProductResponseDto.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .description(savedProduct.getDescription())
                .price(savedProduct.getPrice())
                .category(savedProduct.getCategory())
                .createdAt(savedProduct.getCreatedAt())
                .updatedAt(savedProduct.getUpdatedAt())
                .build();
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> productList = productRepository.findAll()
                .stream()
                .map(product -> ProductResponseDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .category(product.getCategory())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .build())
                .toList();

        return productList;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }

    @Override
    public ProductResponseDto updatePartialProduct(Long id, ProductPatchDto reqDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (reqDto.getName() != null) {
            product.setName(reqDto.getName());
        }
        if (reqDto.getDescription() != null) {
            product.setDescription(reqDto.getDescription());
        }
        if (reqDto.getPrice() != null) {
            product.setPrice(Double.parseDouble(reqDto.getPrice()));
        }
        if (reqDto.getCategory() != null) {
            product.setCategory(reqDto.getCategory());
        }

        Product updatedProduct = productRepository.save(product);

        return ProductResponseDto.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }


}
