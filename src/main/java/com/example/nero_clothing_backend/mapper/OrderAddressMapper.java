package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressResponseDto;
import com.example.nero_clothing_backend.model.entity.OrderAddress;

public class OrderAddressMapper {
    public static OrderAddress toEntity(OrderAddressRequestDto addressDto) {
        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setStreet(addressDto.getStreet());
        orderAddress.setBuilding(addressDto.getBuilding());
        orderAddress.setApartment(addressDto.getApartment());
        orderAddress.setZipCode(addressDto.getZipCode());
        orderAddress.setCity(addressDto.getCity());
        orderAddress.setCountry(addressDto.getCountry());

        return orderAddress;
    }

    public static OrderAddressResponseDto toDto(OrderAddress orderAddress) {
        return OrderAddressResponseDto.builder()
                .id(orderAddress.getId())
                .street(orderAddress.getStreet())
                .building(orderAddress.getBuilding())
                .apartment(orderAddress.getApartment())
                .zipCode(orderAddress.getZipCode())
                .city(orderAddress.getCity())
                .country(orderAddress.getCountry())
                .createdAt(orderAddress.getCreatedAt())
                .updatedAt(orderAddress.getUpdatedAt())
                .build();
    }

}
