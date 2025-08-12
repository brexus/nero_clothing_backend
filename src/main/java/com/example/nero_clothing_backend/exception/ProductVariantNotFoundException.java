package com.example.nero_clothing_backend.exception;

public class ProductVariantNotFoundException extends RuntimeException {
    public ProductVariantNotFoundException(Long id) {
        super("Product variant id: " + id + " not found.");
    }
}
