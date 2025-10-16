package com.example.nero_clothing_backend.service.Customer;

import com.example.nero_clothing_backend.mapper.CustomerMapper;
import com.example.nero_clothing_backend.model.dto.Customer.CustomerRequestDto;
import com.example.nero_clothing_backend.model.dto.Customer.CustomerResponseDto;
import com.example.nero_clothing_backend.model.entity.Customer;
import com.example.nero_clothing_backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository CustomerRepository;

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto reqDto) {

        Customer newCustomer = CustomerMapper.toEntity(reqDto);

        Customer savedCustomer = CustomerRepository.save(newCustomer);

        return CustomerMapper.toDto(savedCustomer);
    }
}
