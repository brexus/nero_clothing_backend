package com.example.nero_clothing_backend.exception;

public class ProductVariantNotFound extends RuntimeException {
    public ProductVariantNotFound(Long id) {
        super("Product variant not found with id: " + id);
    }
}
