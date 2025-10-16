package com.example.nero_clothing_backend.service.Product;

import com.example.nero_clothing_backend.exception.CustomMessageException;
import com.example.nero_clothing_backend.exception.ProductNotFoundException;
import com.example.nero_clothing_backend.exception.ProductVariantNotFound;
import com.example.nero_clothing_backend.mapper.ProductImageMapper;
import com.example.nero_clothing_backend.mapper.ProductMapper;
import com.example.nero_clothing_backend.mapper.ProductVariantMapper;
import com.example.nero_clothing_backend.model.dto.Product.ProductPageInfoDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageResponseDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import com.example.nero_clothing_backend.repository.ProductRepository;
import com.example.nero_clothing_backend.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    public Page<ProductResponseDto> getAllProducts(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (category != null) {
            CategoryEnum enumCategory;

            try {
                enumCategory = CategoryEnum.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException ex) {
                throw new CustomMessageException("Invalid category: " + category);
            }

            return productRepository.findByCategory(enumCategory, pageable)
                    .map(ProductMapper::toDto);
        }

        return productRepository.findAll(pageable)
                .map(ProductMapper::toDto);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);
    }

    @Override
    public ProductResponseDto updatePartialProduct(Long id, ProductPatchDto reqDto) {
        if (isPatchDtoEmpty(reqDto)) {
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
        if (reqDto.getPrice() != null) {
            product.setPrice(reqDto.getPrice());
        }
        if (reqDto.getCategory() != null) {
            product.setCategory(reqDto.getCategory());
        }

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toDto(updatedProduct);
    }

    @Override
    public Map<String, Integer> getProductVariantQuantitiesByProductId(Long id) {

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

    @Override
    public ProductVariantResponseDto getProductVariantByProductIdAndSize(Long productId, ProductSizeEnum size) {
        ProductVariant productVariant = productRepository.findProductVariantByProductIdAndSize(productId, size);

        if (productVariant == null) {
            throw new ProductVariantNotFound(productId);
        }
        return ProductVariantMapper.toDto(productVariant);
    }

    @Override
    public ProductPageInfoDto getProductPageInfo(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        List<ProductVariant> productVariantList = productVariantRepository.findByProductId(id);

        Map<String, Integer> variantQuantities = productVariantList.stream()
                .collect(Collectors.toMap(
                        variant -> variant.getSize() + "_size",
                        ProductVariant::getStockQuantity
                ));


        List<ProductImageResponseDto> productImages = product.getProductImages()
                .stream()
                .map(ProductImageMapper::toDto)
                .toList();

        return ProductPageInfoDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .variantQuantities(variantQuantities)
                .productImages(productImages)
                .build();
    }

    private boolean isPatchDtoEmpty(ProductPatchDto reqDto) {
        String s = "ala ma kota";
        List<String> lista = Arrays.asList(s.split(" ")).reversed();
        lista = lista.stream()
                .filter(str -> str != null && !str.isEmpty())
                .collect(toList());

        List<String> list = Arrays.asList(s.split("\\s+"))
                .stream()
                .filter(str -> !str.isEmpty())
                .toList()
                .reversed();


        return (reqDto.getName() == null || reqDto.getName().isEmpty()) &&
                (reqDto.getDescription() == null || reqDto.getDescription().isEmpty()) &&
                (reqDto.getPrice() == null) &&
                (reqDto.getCategory() == null);
    }


}
