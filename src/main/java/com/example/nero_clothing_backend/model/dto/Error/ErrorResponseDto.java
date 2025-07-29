package com.example.nero_clothing_backend.model.dto.Error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorResponseDto {
    private String error; // e.g., "Not Found", "Bad Request"
    private String message; // e.g., "Address not found with id: 1"
    private int status; // e.g., "404 Not Found"
    private LocalDateTime timestamp; // e.g., "2023-10-01T12:00:00Z"
    private List<Map<String, String>> errors;
}
