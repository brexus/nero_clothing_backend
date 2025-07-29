package com.example.nero_clothing_backend.handler;


import com.example.nero_clothing_backend.model.dto.Error.ErrorResponseDto;
import com.example.nero_clothing_backend.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ErrorResponseDto handleGenericException(Exception ex, HttpStatus httpStatus) {
        return ErrorResponseDto.builder()
                .error(httpStatus.getReasonPhrase())
                .message(ex.getMessage())
                .status(httpStatus.value())
                .timestamp(LocalDateTime.now())
                .errors(null)
                .build();
    }

    // address
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAddressNotFound(AddressNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handleGenericException(ex, HttpStatus.NOT_FOUND));
    }


    // user
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UserNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handleGenericException(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(handleGenericException(ex, HttpStatus.CONFLICT));
    }

    // product image
    @ExceptionHandler(ProductImageNotFound.class)
    public ResponseEntity<ErrorResponseDto> handleProductImageNotFound(ProductImageNotFound ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handleGenericException(ex, HttpStatus.NOT_FOUND));
    }

    //product
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFound(ProductNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handleGenericException(ex, HttpStatus.NOT_FOUND));
    }

    //order
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleOrderNotFoundException(OrderNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handleGenericException(ex, HttpStatus.NOT_FOUND));
    }

    //product variant stock
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponseDto> handleInsufficientStockException(InsufficientStockException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(handleGenericException(ex, HttpStatus.CONFLICT));
    }

    // category
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCategoryNotFound(CategoryNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(handleGenericException(ex, HttpStatus.NOT_FOUND));
    }

    // validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(error.getField(), error.getDefaultMessage());
                    return errorMap;
                })
                .collect(Collectors.toList());

        ErrorResponseDto error = ErrorResponseDto.builder()
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}