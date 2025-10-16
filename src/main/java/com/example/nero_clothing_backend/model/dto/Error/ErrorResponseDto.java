package com.example.nero_clothing_backend.model.dto.Error;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class ErrorResponseDto {
    private String error;
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private List<Map<String, String>> errors;

}
