package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.Order.OrderRequestDto;
import com.example.nero_clothing_backend.model.dto.Order.OrderResponseDto;
import com.example.nero_clothing_backend.service.Order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    //private
    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto reqDto) {
        OrderResponseDto resDto = orderService.createOrder(reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updatePartialOrder(@RequestBody OrderRequestDto reqDto, @PathVariable Long id) {
        OrderResponseDto resDto = orderService.updatePartialOrder(reqDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }
}
