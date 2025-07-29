package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.mapper.ProductVariantMapper;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductVariant.ProductVariantResponseDto;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import com.example.nero_clothing_backend.service.ProductVariant.ProductVariantService;
import com.example.nero_clothing_backend.service.ProductVariant.ProductVariantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-variant")
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @PostMapping("/")
    private ResponseEntity<ProductVariantResponseDto> createProductVariant(@RequestBody ProductVariantRequestDto reqDto) {
        ProductVariantResponseDto resDto = productVariantService.createProductVariant(reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

}
