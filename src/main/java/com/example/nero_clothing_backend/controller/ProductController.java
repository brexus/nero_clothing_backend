package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.Product.ProductPatchDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.service.Product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    private ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto reqDto) {
        ProductResponseDto resDto = productService.createProduct(reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        ProductResponseDto resDto = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/")
    private ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> resDto = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
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
