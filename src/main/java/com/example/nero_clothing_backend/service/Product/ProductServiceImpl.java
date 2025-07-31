package com.example.nero_clothing_backend.service.Product;

import com.example.nero_clothing_backend.exception.CustomMessageException;
import com.example.nero_clothing_backend.mapper.ProductMapper;
import com.example.nero_clothing_backend.model.dto.Address.AddressPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.exception.ProductNotFoundException;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import com.example.nero_clothing_backend.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

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
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
// tutaj ta kwestia soft delete jakbys chcial
        productRepository.delete(product);
    }

    @Override
    public ProductResponseDto updatePartialProduct(Long id, ProductPatchDto reqDto) {
        // w kontrolerze masz @Valid
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

            // tutaj tez case z enumem i doublem, w dto lepiej
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

    private boolean isPatchDtoEmpty(ProductPatchDto reqDto) {
        return (reqDto.getName() == null || reqDto.getName().isEmpty()) &&
                (reqDto.getDescription() == null || reqDto.getDescription().isEmpty()) &&
                (reqDto.getPrice() == null || reqDto.getPrice().isEmpty()) &&
                (reqDto.getCategory() == null || reqDto.getCategory().isEmpty());
    }


}
