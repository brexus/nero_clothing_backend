package com.example.nero_clothing_backend.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

//    @ExceptionHandler(AddressNotFound.class)
//    public ResponseEntity<Object> handleAddressNotFoundException(AddressNotFound ex) {
//        Map<String, String> error = Collections.singletonMap("error", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }
}