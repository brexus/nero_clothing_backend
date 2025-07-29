package com.example.nero_clothing_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductImageNotFound extends RuntimeException {

    public ProductImageNotFound(Long id) {
        super("Image with id " + id + " not found");
    }
}