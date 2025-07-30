package com.example.nero_clothing_backend.service.Address;


import com.example.nero_clothing_backend.model.dto.Address.AddressPatchDto;
import com.example.nero_clothing_backend.model.dto.Address.AddressRequestDto;
import com.example.nero_clothing_backend.model.dto.Address.AddressResponseDto;

import java.util.List;

public interface AddressService {

    AddressResponseDto createAddress(AddressRequestDto dto);
    List<AddressResponseDto> getAllAddresses();
    AddressResponseDto getAddressById(Long id);
    AddressResponseDto updatePartialAddress(Long id, AddressPatchDto reqDto);
    void deleteAddress(Long id);

}
