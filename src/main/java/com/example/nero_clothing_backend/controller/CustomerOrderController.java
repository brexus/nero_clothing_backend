package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderRequestDto;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderResponseDto;
import com.example.nero_clothing_backend.service.CustomerOrder.CustomerOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @PostMapping("/")
    public ResponseEntity<CustomerOrderResponseDto> createCustomerOrder(@Valid @RequestBody CustomerOrderRequestDto reqDto) {
        CustomerOrderResponseDto resDto = customerOrderService.createCustomerOrder(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerOrderResponseDto> updatePartialCustomerOrder(@RequestBody CustomerOrderRequestDto reqDto, @PathVariable Long id) {
        CustomerOrderResponseDto resDto = customerOrderService.updatePartialCustomerOrder(reqDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

}
