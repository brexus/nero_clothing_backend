package com.example.nero_clothing_backend.exception;

public class RequestIsEmptyException extends RuntimeException {
    public RequestIsEmptyException(String message) {
        super(message);
    }
}
