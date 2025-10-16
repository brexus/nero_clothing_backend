package com.example.nero_clothing_backend.service.ProductVariant;

import com.example.nero_clothing_backend.exception.CustomMessageException;
import com.example.nero_clothing_backend.exception.ProductNotFoundException;
import com.example.nero_clothing_backend.exception.ProductVariantNotFoundException;
import com.example.nero_clothing_backend.mapper.ProductVariantMapper;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantPatchDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import com.example.nero_clothing_backend.repository.ProductImageRepository;
import com.example.nero_clothing_backend.repository.ProductRepository;
import com.example.nero_clothing_backend.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public ProductVariantResponseDto createProductVariant(ProductVariantRequestDto reqDto) {
        if (reqDto == null || reqDto.getProductId() == null) {
            throw new CustomMessageException("Request is required.");
        }

        ProductVariant productVariant = ProductVariantMapper.toEntity(reqDto);

        Product product = productRepository.findById(reqDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(reqDto.getProductId()));
        productVariant.setProduct(product);

        ProductVariant savedProductVariant = productVariantRepository.save(productVariant);

        return ProductVariantMapper.toDto(savedProductVariant);
    }

    @Override
    public List<ProductVariantResponseDto> getAllProductVariants() {
        List<ProductVariant> productVariants = productVariantRepository.findAll();

        if (productVariants.isEmpty()) {
            throw new CustomMessageException("No product variants found.");
        }

        return productVariants
                .stream()
                .map(ProductVariantMapper::toDto)
                .toList();
    }

    @Override
    public ProductVariantResponseDto getProductVariantById(Long id) {
        if (id == null) {
            throw new CustomMessageException("Product variant ID is required.");
        }

        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new ProductVariantNotFoundException(id));

        return ProductVariantMapper.toDto(productVariant);
    }

    @Override
    public ProductVariantResponseDto updatePartialProductVariant(Long id, ProductVariantPatchDto reqDto) {
        if (isPatchDtoEmpty(reqDto)) {
            throw new CustomMessageException("Request cannot be blank.");
        }

        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if ((reqDto.getProductId() != null) && (!reqDto.getProductId().toString().isEmpty())) {
            Product product = productRepository.findById(reqDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(reqDto.getProductId()));
            productVariant.setProduct(product);
        }
        if ((reqDto.getStockQuantity() != null) && (!reqDto.getStockQuantity().toString().isEmpty())) {
            productVariant.setStockQuantity(reqDto.getStockQuantity());
        }
        if (reqDto.getSize() != null) {
            productVariant.setSize(reqDto.getSize());
        }

        ProductVariant savedProductVariant = productVariantRepository.save(productVariant);

        return ProductVariantMapper.toDto(savedProductVariant);

    }

    @Override
    public void deleteProductVariant(Long id) {
        if (id == null) {
            throw new CustomMessageException("Product variant ID is required.");
        }

        ProductVariant productVariant = productVariantRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productVariantRepository.delete(productVariant);
    }


    @Override
    public Map<String, String> getBasicInfoOfProductVariantById(Long id) {

        ProductVariant productVariant = productVariantRepository.findById(id).orElseThrow(() -> new ProductVariantNotFoundException(id));
        Product product = productVariant.getProduct();

        String imageUrl = productImageRepository.findByProductId(product.getId()).getFirst().getImageUrl();

        return Map.of(
                "productVariantId", productVariant.getId().toString(),
                "productId", product.getId().toString(),
                "name", product.getName(),
                "size", productVariant.getSize().toString(),
                "imageUrl", imageUrl,
                "productPrice", product.getPrice().toString()
        );

    }


    private boolean isPatchDtoEmpty(ProductVariantPatchDto reqDto) {
        return (reqDto.getSize() == null) &&
                (reqDto.getStockQuantity() == null) &&
                (reqDto.getProductId() == null);
    }
}
