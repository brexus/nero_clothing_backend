package com.example.nero_clothing_backend.service.Product;

import com.example.nero_clothing_backend.exception.CustomMessageException;
import com.example.nero_clothing_backend.exception.ProductVariantNotFound;
import com.example.nero_clothing_backend.mapper.ProductMapper;
import com.example.nero_clothing_backend.model.dto.Address.AddressPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.exception.ProductNotFoundException;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import com.example.nero_clothing_backend.repository.ProductRepository;
import com.example.nero_clothing_backend.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto reqDto) {
        Product product = ProductMapper.toEntity(reqDto);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> productList = productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
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
        if(isPatchDtoEmpty(reqDto)) {
            throw new CustomMessageException("Request cannot be blank.");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if (reqDto.getName() != null && !reqDto.getName().isEmpty()) {
            product.setName(reqDto.getName());
        }
        if (reqDto.getDescription() != null && !reqDto.getDescription().isEmpty()) {
            product.setDescription(reqDto.getDescription());
        }
        if (reqDto.getPrice() != null && !reqDto.getPrice().isEmpty()) {
            product.setPrice(Double.parseDouble(reqDto.getPrice()));
        }
        if (reqDto.getCategory() != null && !reqDto.getCategory().isEmpty()) {

            CategoryEnum enumCategory;
            try {
                enumCategory = CategoryEnum.valueOf(reqDto.getCategory().toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new CustomMessageException("Invalid category: " + (reqDto.getCategory()));
            }

            product.setCategory(enumCategory);
        }

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(String category) {
        CategoryEnum enumCategory;

        try {
            enumCategory = CategoryEnum.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new CustomMessageException("Invalid category: " + category);
        }

        return productRepository.findByCategory(enumCategory)
                .stream()
                .map(ProductMapper::toDto)
                .toList();

    }

    @Override
    public Map<String,Integer> getProductVariantQuantitiesByProductId(Long id) {

        List<ProductVariant> productVariantList = productVariantRepository.findByProductId(id);

        if (productVariantList.isEmpty()) {
            throw new ProductVariantNotFound(id);
        }

        return productVariantList.stream()
                .collect(Collectors.toMap(
                        variant -> variant.getSize() + "_size",
                        ProductVariant::getStockQuantity
                ));
    }

    private boolean isPatchDtoEmpty(ProductPatchDto reqDto) {
        return (reqDto.getName() == null || reqDto.getName().isEmpty()) &&
                (reqDto.getDescription() == null || reqDto.getDescription().isEmpty()) &&
                (reqDto.getPrice() == null || reqDto.getPrice().isEmpty()) &&
                (reqDto.getCategory() == null || reqDto.getCategory().isEmpty());
    }


}
