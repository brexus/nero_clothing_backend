package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderRequestDto;
import com.example.nero_clothing_backend.model.dto.CustomerOrder.CustomerOrderResponseDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemResponseDto;
import com.example.nero_clothing_backend.model.entity.CustomerOrder;
import com.example.nero_clothing_backend.model.entity.OrderAddress;

import java.util.List;

public class CustomerOrderMapper {
    public static CustomerOrder toEntity(CustomerOrderRequestDto reqDto) {

        OrderAddress orderAddress = OrderAddress.builder()
                .city(reqDto.getShippingAddress().getCity())
                .street(reqDto.getShippingAddress().getStreet())
                .building(reqDto.getShippingAddress().getBuilding())
                .apartment(reqDto.getShippingAddress().getApartment())
                .zipCode(reqDto.getShippingAddress().getZipCode())
                .country(reqDto.getShippingAddress().getCountry())
                .build();

        return CustomerOrder.builder()
                .customer(null)
                .firstName(reqDto.getFirstName())
                .lastName(reqDto.getLastName())
                .phoneNumber(reqDto.getPhoneNumber())
                .totalAmount(null)
                .status(null)
                .paymentMethod(reqDto.getPaymentMethod())
                .shippingDate(null)
                .deliveryDate(null)
                .items(null)
                .shippingAddress(orderAddress)
                .build();
    }

    public static CustomerOrderResponseDto toDto(CustomerOrder order) {

        List<OrderItemResponseDto> orderItemResponseDtos = order.getItems().stream().map(OrderItemMapper::toDto).toList();

        return CustomerOrderResponseDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .deliveryDate(order.getDeliveryDate())
                .shippingDate(order.getShippingDate())
                .customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .items(orderItemResponseDtos)
                .deliveryAddress(order.getShippingAddress() != null ? OrderAddressMapper.toDto(order.getShippingAddress()) : null)
                .build();
    }
}
