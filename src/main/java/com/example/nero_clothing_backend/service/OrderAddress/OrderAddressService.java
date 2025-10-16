package com.example.nero_clothing_backend.service.OrderAddress;


import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressPatchDto;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressResponseDto;

import java.util.List;

public interface OrderAddressService {

    OrderAddressResponseDto createOrderAddress(OrderAddressRequestDto dto);

    List<OrderAddressResponseDto> getAllOrderAddresses();

    OrderAddressResponseDto getOrderAddressById(Long id);

    OrderAddressResponseDto updatePartialOrderAddress(Long id, OrderAddressPatchDto reqDto);

    void deleteOrderAddress(Long id);

}
