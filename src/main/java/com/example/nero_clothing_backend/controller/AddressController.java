package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.Address.AddressPatchDto;
import com.example.nero_clothing_backend.model.dto.Address.AddressRequestDto;
import com.example.nero_clothing_backend.model.dto.Address.AddressResponseDto;
import com.example.nero_clothing_backend.service.Address.AddressServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressServiceImpl addressService;

    @PostMapping("/")
    public ResponseEntity<AddressResponseDto> createAddress(@Valid @RequestBody AddressRequestDto reqDto) {
        AddressResponseDto resDto = addressService.createAddress(reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressResponseDto>> getAllPosts(){
        List<AddressResponseDto> addresses = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable Long id) {
        AddressResponseDto res = addressService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@Valid @PathVariable Long id, @RequestBody AddressPatchDto reqDto) {
        AddressResponseDto res = addressService.updatePartialAddress(id, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Address with id " + id + " deleted successfully."));
    }

}
