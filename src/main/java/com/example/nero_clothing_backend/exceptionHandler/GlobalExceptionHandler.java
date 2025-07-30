package com.example.nero_clothing_backend.exceptionHandler;

import com.example.nero_clothing_backend.model.dto.Error.ErrorResponseDto;
import com.example.nero_clothing_backend.exception.*;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.*;
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

        private ResponseEntity<ErrorResponseDto> buildErrorResponse(List<Map<String, String>> errorList) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .error("Bad Request")
                .message("Validation failed")
                .status(400)
                .timestamp(LocalDateTime.now())
                .errors(errorList)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorResponseDto> handleJsonParseErrors(HttpMessageNotReadableException ex) {
//        List<Map<String, String>> errors = new ArrayList<>();
//
//        // Sprawdź czy to błąd ENUM
//        Throwable cause = ex.getCause();
//        if (cause instanceof InvalidFormatException) {
//            InvalidFormatException formatEx = (InvalidFormatException) cause;
//
//            // Pobierz nazwę pola
//            String fieldName = "unknown";
//            if (formatEx.getPath() != null && !formatEx.getPath().isEmpty()) {
//                fieldName = formatEx.getPath().get(formatEx.getPath().size() - 1).getFieldName();
//            }
//
//            // Sprawdź czy to CategoryEnum
//            if (formatEx.getTargetType() != null && formatEx.getTargetType().equals(CategoryEnum.class)) {
//                Map<String, String> errorMap = new HashMap<>();
//                errorMap.put(fieldName, "Invalid category. Must be one of: " + Arrays.toString(CategoryEnum.values()));
//                errors.add(errorMap);
//            } else {
//                // Inne błędy formatowania
//                Map<String, String> errorMap = new HashMap<>();
//                errorMap.put(fieldName, "Invalid value format");
//                errors.add(errorMap);
//            }
//        } else {
//            // Ogólny błąd JSON
//            Map<String, String> errorMap = new HashMap<>();
//            errorMap.put("request", "Invalid request format");
//            errors.add(errorMap);
//        }
//
//        // Zwróć w tym samym formacie co validation errors
//        ErrorResponseDto error = ErrorResponseDto.builder()
//                .error("Bad Request")
//                .message("Validation failed")
//                .status(HttpStatus.BAD_REQUEST.value())
//                .timestamp(LocalDateTime.now())
//                .errors(errors)
//                .build();
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }

    // validation - zostaje bez zmian
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(Map.of(error.getField(), error.getDefaultMessage()))
        );
        return buildErrorResponse(errors);
    }



    // custom message
    @ExceptionHandler(CustomMessageException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomMessageException(CustomMessageException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(handleGenericException(ex, HttpStatus.BAD_REQUEST));
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
}