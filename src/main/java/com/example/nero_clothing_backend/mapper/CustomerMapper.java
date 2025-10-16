package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.Customer.CustomerRequestDto;
import com.example.nero_clothing_backend.model.dto.Customer.CustomerResponseDto;
import com.example.nero_clothing_backend.model.entity.Customer;


public class CustomerMapper {
    public static Customer toEntity(CustomerRequestDto reqDto) {

        return Customer.builder()
                .guestEmail(reqDto.getGuestEmail())
                .user(null)
                .build();
    }

    public static CustomerResponseDto toDto(Customer customer) {

        return CustomerResponseDto.builder()
                .id(customer.getId())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .user(customer.getUser())
                .guestEmail(customer.getGuestEmail())
                .build();
    }
}
