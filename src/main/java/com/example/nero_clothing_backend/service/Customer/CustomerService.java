package com.example.nero_clothing_backend.service.Customer;

import com.example.nero_clothing_backend.model.dto.Customer.CustomerRequestDto;
import com.example.nero_clothing_backend.model.dto.Customer.CustomerResponseDto;

public interface CustomerService {

    CustomerResponseDto createCustomer(CustomerRequestDto reqDto);

}
