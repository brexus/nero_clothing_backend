//package com.example.nero_clothing_backend.exceptionHandler;
//
//import com.example.nero_clothing_backend.model.dto.Error.ErrorResponseDto;
//import com.example.nero_clothing_backend.model.enums.CategoryEnum;
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.util.*;
//
//@RestControllerAdvice
//public class ValidationExceptionHandler {
//
//    private ResponseEntity<ErrorResponseDto> buildErrorResponse(List<Map<String, String>> errorList) {
//        ErrorResponseDto response = ErrorResponseDto.builder()
//                .error("Bad Request")
//                .message("Validation failed")
//                .status(400)
//                .timestamp(LocalDateTime.now())
//                .errors(errorList)
//                .build();
//
//        return ResponseEntity.badRequest().body(response);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
//        List<Map<String, String>> errors = new ArrayList<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.add(Map.of(error.getField(), error.getDefaultMessage()))
//        );
//        return buildErrorResponse(errors);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorResponseDto> handleJsonParseErrors(HttpMessageNotReadableException ex) {
//        List<Map<String, String>> errors = new ArrayList<>();
//
//        if (ex.getCause() instanceof InvalidFormatException formatEx) {
//            Class<?> targetType = formatEx.getTargetType();
//
//            if (targetType.equals(CategoryEnum.class)) {
//                errors.add(Map.of(
//                        "category", "Invalid category. Must be one of: " + Arrays.toString(CategoryEnum.values())
//                ));
//            }
//        }
//
//        if (errors.isEmpty()) {
//            errors.add(Map.of("body", "Invalid request format"));
//        }
//
//        return buildErrorResponse(errors);
//    }
//}
