package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.mapper.ProductVariantMapper;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantPatchDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import com.example.nero_clothing_backend.service.ProductVariant.ProductVariantService;
import com.example.nero_clothing_backend.service.ProductVariant.ProductVariantServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product-variant")
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @PostMapping("/")
    private ResponseEntity<ProductVariantResponseDto> createProductVariant(@Valid @RequestBody ProductVariantRequestDto reqDto) {
        ProductVariantResponseDto resDto = productVariantService.createProductVariant(reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/")
    private ResponseEntity<List<ProductVariantResponseDto>> getAllProductVariants() {
        List<ProductVariantResponseDto> resDtos = productVariantService.getAllProductVariants();
        return ResponseEntity.status(HttpStatus.OK).body(resDtos);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductVariantResponseDto> getProductVariantById(@PathVariable Long id) {
        ProductVariantResponseDto resDto = productVariantService.getProductVariantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<ProductVariantResponseDto> updatePartialProductVariant(@PathVariable Long id, @Valid @RequestBody ProductVariantPatchDto reqDto) {
        ProductVariantResponseDto resDto = productVariantService.updatePartialProductVariant(id, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Map<String, String>> deleteProductVariant(@PathVariable Long id) {
        productVariantService.deleteProductVariant(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Product variant with id: " + id + " deleted successfully."));
    }



}
