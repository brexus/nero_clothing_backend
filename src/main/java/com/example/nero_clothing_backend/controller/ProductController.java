package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.Product.ProductPageInfoDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import com.example.nero_clothing_backend.service.Product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    private ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto reqDto) {
        ProductResponseDto resDto = productService.createProduct(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/")
    private ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        Page<ProductResponseDto> resDto = productService.getAllProducts(category, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        ProductResponseDto resDto = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/product-page-info/{id}")
    private ResponseEntity<ProductPageInfoDto> getProductPageInfo(@PathVariable Long id) {
        ProductPageInfoDto resDto = productService.getProductPageInfo(id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/get-product-variant/{productId}/{size}")
    private ResponseEntity<ProductVariantResponseDto> getProductVariantByProductIdAndSize(@PathVariable Long productId, @PathVariable ProductSizeEnum size) {
        ProductVariantResponseDto resDto = productService.getProductVariantByProductIdAndSize(productId, size);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/variant-quantities/{id}")
    private ResponseEntity<Map<String, Integer>> getProductVariantQuantitiesByProductId(@PathVariable Long id) {
        Map<String, Integer> res = productService.getProductVariantQuantitiesByProductId(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<ProductResponseDto> updatePartialProduct(@PathVariable Long id, @Valid @RequestBody ProductPatchDto reqDto) {
        ProductResponseDto resDto = productService.updatePartialProduct(id, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Product with id " + id + " deleted successfully."));
    }

}
