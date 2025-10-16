package com.example.nero_clothing_backend.service.OrderAddress;

import com.example.nero_clothing_backend.exception.AddressNotFoundException;
import com.example.nero_clothing_backend.exception.CustomMessageException;
import com.example.nero_clothing_backend.mapper.OrderAddressMapper;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressPatchDto;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressResponseDto;
import com.example.nero_clothing_backend.model.entity.OrderAddress;
import com.example.nero_clothing_backend.repository.OrderAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderAddressServiceImpl implements OrderAddressService {

    private final OrderAddressRepository addressRepository;

    public OrderAddressResponseDto createOrderAddress(OrderAddressRequestDto reqDto) {
        OrderAddress address = OrderAddressMapper.toEntity(reqDto);
        OrderAddress saved = addressRepository.save(address);
        return OrderAddressMapper.toDto(saved);
    }

    @Override
    public List<OrderAddressResponseDto> getAllOrderAddresses() {
        List<OrderAddress> addresses = addressRepository.findAll();
        List<OrderAddressResponseDto> addressesResponseDto = new ArrayList<>();

        if (addresses.isEmpty()) {
            return addressesResponseDto;
        }

        for (OrderAddress address : addresses) {
            addressesResponseDto.add(OrderAddressMapper.toDto(address));
        }

        return addressesResponseDto;
    }

    @Override
    public OrderAddressResponseDto getOrderAddressById(Long id) {
        OrderAddress address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        return OrderAddressMapper.toDto(address);
    }

    @Override
    public OrderAddressResponseDto updatePartialOrderAddress(Long id, OrderAddressPatchDto reqDto) {
        if (isPatchDtoEmpty(reqDto)) {
            throw new CustomMessageException("Request cannot be blank.");
        }

        OrderAddress oldAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));

        if ((reqDto.getStreet() != null) && !reqDto.getStreet().isEmpty()) {
            oldAddress.setStreet(reqDto.getStreet());
        }
        if ((reqDto.getBuilding() != null) && !reqDto.getBuilding().isEmpty()) {
            oldAddress.setBuilding(reqDto.getBuilding());
        }
        if ((reqDto.getApartment() != null) && !reqDto.getApartment().isEmpty()) {
            oldAddress.setApartment(reqDto.getApartment());
        }
        if ((reqDto.getZipCode() != null) && !reqDto.getZipCode().isEmpty()) {
            oldAddress.setZipCode(reqDto.getZipCode());
        }
        if ((reqDto.getCity() != null) && !reqDto.getCity().isEmpty()) {
            oldAddress.setCity(reqDto.getCity());
        }
        if ((reqDto.getCountry() != null) && !reqDto.getCountry().isEmpty()) {
            oldAddress.setCountry(reqDto.getCountry());
        }
        OrderAddress updatedAddress = addressRepository.save(oldAddress);

        return OrderAddressMapper.toDto(updatedAddress);
    }

    @Override
    public void deleteOrderAddress(Long id) {
        OrderAddress address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        addressRepository.delete(address);
    }

    private boolean isPatchDtoEmpty(OrderAddressPatchDto reqDto) {
        return (reqDto.getStreet() == null || reqDto.getStreet().isEmpty()) &&
                (reqDto.getBuilding() == null || reqDto.getBuilding().isEmpty()) &&
                (reqDto.getApartment() == null || reqDto.getApartment().isEmpty()) &&
                (reqDto.getZipCode() == null || reqDto.getZipCode().isEmpty()) &&
                (reqDto.getCity() == null || reqDto.getCity().isEmpty()) &&
                (reqDto.getCountry() == null || reqDto.getCountry().isEmpty());
    }
}
