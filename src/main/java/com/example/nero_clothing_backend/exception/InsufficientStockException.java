package com.example.nero_clothing_backend.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long id, Integer available, Integer ordered) {
        super("Insufficient quantity of product id: " + id + ". Available: " + available + ", ordered: " + ordered + ".");
    }
}
