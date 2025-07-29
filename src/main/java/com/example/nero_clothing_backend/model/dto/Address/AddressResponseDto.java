package com.example.nero_clothing_backend.model.dto.Address;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AddressResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String street;
    private String building;
    private String apartment;
    private String zipCode;
    private String city;
    private String country;

}
