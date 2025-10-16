package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageRequestDto;
import com.example.nero_clothing_backend.model.dto.ProductImage.ProductImageResponseDto;
import com.example.nero_clothing_backend.service.ProductImage.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product/image")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

    @PostMapping("/")
    public ResponseEntity<ProductImageResponseDto> createProductImage(@RequestBody ProductImageRequestDto reqDto) {
        ProductImageResponseDto resDto = productImageService.createProductImage(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProductImageResponseDto>> getProductImagesByProductId(@PathVariable Long id) {
        List<ProductImageResponseDto> imgList = productImageService.getProductImagesByProductId(id);
        return ResponseEntity.status(HttpStatus.OK).body(imgList);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductImageResponseDto>> getAllProductImages() {
        List<ProductImageResponseDto> imgList = productImageService.getAllProductImages();
        return ResponseEntity.status(HttpStatus.OK).body(imgList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductImageResponseDto> updatePartialProductImage(@PathVariable Long id, @RequestBody ProductImageRequestDto reqDto) {
        ProductImageResponseDto resDto = productImageService.updatePartialProductImage(id, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProductImage(@PathVariable Long id) {
        productImageService.deleteProductImage(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Product image with id " + id + " deleted successfully"));
    }

}
