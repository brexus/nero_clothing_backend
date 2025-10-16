package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressPatchDto;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressResponseDto;
import com.example.nero_clothing_backend.service.OrderAddress.OrderAddressServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order-address")
@RequiredArgsConstructor
public class OrderAddressController {

    private final OrderAddressServiceImpl addressService;

    @PostMapping("/")
    public ResponseEntity<OrderAddressResponseDto> createOrderAddress(@Valid @RequestBody OrderAddressRequestDto reqDto) {
        OrderAddressResponseDto resDto = addressService.createOrderAddress(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderAddressResponseDto>> getAllOrderAddresses() {
        List<OrderAddressResponseDto> addresses = addressService.getAllOrderAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderAddressResponseDto> getOrderAddressById(@PathVariable Long id) {
        OrderAddressResponseDto res = addressService.getOrderAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderAddressResponseDto> updateOrderAddress(@PathVariable Long id, @Valid @RequestBody OrderAddressPatchDto reqDto) {
        OrderAddressResponseDto res = addressService.updatePartialOrderAddress(id, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteOrderAddress(@PathVariable Long id) {
        addressService.deleteOrderAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Address with id " + id + " deleted successfully."));
    }

}
