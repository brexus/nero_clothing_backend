package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.Address.AddressRequestDto;
import com.example.nero_clothing_backend.model.dto.Address.AddressResponseDto;
import com.example.nero_clothing_backend.model.entity.Address;

public class AddressMapper {
    public static Address toEntity(AddressRequestDto addressDto) {
        if (addressDto == null) {
            return null;
        }

        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setBuilding(addressDto.getBuilding());
        address.setApartment(addressDto.getApartment());
        address.setZipCode(addressDto.getZipCode());
        address.setCity(addressDto.getCity());
        address.setCountry(addressDto.getCountry());

        return address;
    }

    public static AddressResponseDto toDto(Address address) {
        if (address == null) {
            return null;
        }

        return AddressResponseDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .building(address.getBuilding())
                .apartment(address.getApartment())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }

}
