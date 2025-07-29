package com.example.nero_clothing_backend.service.Address;

import com.example.nero_clothing_backend.model.dto.Address.AddressPatchDto;
import com.example.nero_clothing_backend.model.dto.Address.AddressRequestDto;
import com.example.nero_clothing_backend.model.dto.Address.AddressResponseDto;
import com.example.nero_clothing_backend.exception.AddressNotFoundException;
import com.example.nero_clothing_backend.mapper.AddressMapper;
import com.example.nero_clothing_backend.model.entity.Address;
import com.example.nero_clothing_backend.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressResponseDto createAddress(AddressRequestDto reqDto) {
        Address address = AddressMapper.toEntity(reqDto);
        Address saved = addressRepository.save(address);
        return AddressMapper.toDto(saved);
    }

    @Override
    public List<AddressResponseDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        List<AddressResponseDto> addressesResponseDto = new ArrayList<>();

        if (addresses.isEmpty()) {
            return addressesResponseDto;
        }

        for (Address address : addresses) {
            addressesResponseDto.add(AddressMapper.toDto(address));
        }

        return addressesResponseDto;
    }

    @Override
    public AddressResponseDto getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        return AddressMapper.toDto(address);
    }

    @Override
    public AddressResponseDto updatePartialAddress(Long id, AddressPatchDto reqDto) {
            Address oldAddress = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));

        if(reqDto.getStreet() != null) {
            oldAddress.setStreet(reqDto.getStreet());
        }
        if(reqDto.getBuilding() != null) {
            oldAddress.setBuilding(reqDto.getBuilding());
        }
        if(reqDto.getApartment() != null) {
            oldAddress.setApartment(reqDto.getApartment());
        }
        if(reqDto.getPostalCode() != null) {
            oldAddress.setZipCode(reqDto.getPostalCode());
        }
        if(reqDto.getCity() != null) {
            oldAddress.setCity(reqDto.getCity());
        }
        if(reqDto.getCountry() != null) {
            oldAddress.setCountry(reqDto.getCountry());
        }
        Address updatedAddress = addressRepository.save(oldAddress);

        return AddressMapper.toDto(updatedAddress);
    }

    @Override
    public AddressResponseDto updateFullAddress(Long id, AddressRequestDto reqDto) {
        Address oldAddress = addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));

        oldAddress.setStreet(reqDto.getStreet());
        oldAddress.setBuilding(reqDto.getBuilding());
        oldAddress.setApartment(reqDto.getApartment());
        oldAddress.setZipCode(reqDto.getZipCode());
        oldAddress.setCity(reqDto.getCity());
        oldAddress.setCountry(reqDto.getCountry());

        Address updatedAddress = addressRepository.save(oldAddress);

        return AddressMapper.toDto(updatedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        addressRepository.delete(address);
    }
}
