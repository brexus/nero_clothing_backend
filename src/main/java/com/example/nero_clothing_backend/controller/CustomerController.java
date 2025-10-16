package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.Customer.CustomerRequestDto;
import com.example.nero_clothing_backend.model.dto.Customer.CustomerResponseDto;
import com.example.nero_clothing_backend.service.Customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<CustomerResponseDto> createCustomer(@Valid @RequestBody CustomerRequestDto reqDto) {
        CustomerResponseDto resDto = customerService.createCustomer(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

}
