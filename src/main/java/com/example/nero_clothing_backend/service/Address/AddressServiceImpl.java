package com.example.nero_clothing_backend.service.Address;

import com.example.nero_clothing_backend.exception.CustomMessageException;
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
        if(isPatchDtoEmpty(reqDto)) {
            throw new CustomMessageException("Request cannot be blank.");
        }

        Address oldAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));

        if((reqDto.getStreet() != null) && !reqDto.getStreet().isEmpty()) {
            oldAddress.setStreet(reqDto.getStreet());
        }
        if((reqDto.getBuilding() != null) && !reqDto.getBuilding().isEmpty()) {
            oldAddress.setBuilding(reqDto.getBuilding());
        }
        if((reqDto.getApartment() != null) && !reqDto.getApartment().isEmpty()) {
            oldAddress.setApartment(reqDto.getApartment());
        }
        if((reqDto.getZipCode() != null) && !reqDto.getZipCode().isEmpty()) {
            oldAddress.setZipCode(reqDto.getZipCode());
        }
        if((reqDto.getCity() != null) && !reqDto.getCity().isEmpty()) {
            oldAddress.setCity(reqDto.getCity());
        }
        if((reqDto.getCountry() != null) && !reqDto.getCountry().isEmpty()) {
            oldAddress.setCountry(reqDto.getCountry());
        }
        Address updatedAddress = addressRepository.save(oldAddress);

        return AddressMapper.toDto(updatedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
        addressRepository.delete(address);
    }

    private boolean isPatchDtoEmpty(AddressPatchDto reqDto) {
        return (reqDto.getStreet() == null || reqDto.getStreet().isEmpty()) &&
                (reqDto.getBuilding() == null || reqDto.getBuilding().isEmpty()) &&
                (reqDto.getApartment() == null || reqDto.getApartment().isEmpty()) &&
                (reqDto.getZipCode() == null || reqDto.getZipCode().isEmpty()) &&
                (reqDto.getCity() == null || reqDto.getCity().isEmpty()) &&
                (reqDto.getCountry() == null || reqDto.getCountry().isEmpty());
    }
}
