package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.Order.OrderRequestDto;
import com.example.nero_clothing_backend.model.dto.Order.OrderResponseDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemResponseDto;
import com.example.nero_clothing_backend.model.embedded.DeliveryAddress;
import com.example.nero_clothing_backend.model.entity.Address;
import com.example.nero_clothing_backend.model.entity.Order;
import com.example.nero_clothing_backend.model.entity.OrderItem;
import com.example.nero_clothing_backend.model.enums.OrderStatus;

import java.util.List;

public class OrderMapper {
    public static Order toEntity(OrderRequestDto reqDto) {

        if (reqDto == null) { // tutaj tak samo masz to @Valid + nawet jesli by nie bylo to wczesniej sprawdzasz
            return null;
        }

        DeliveryAddress address = DeliveryAddress.builder()
                .city(reqDto.getDeliveryCity())
                .street(reqDto.getDeliveryStreet())
                .building(reqDto.getDeliveryBuilding())
                .apartment(reqDto.getDeliveryApartment())
                .zipCode(reqDto.getDeliveryZipCode())
                .country(reqDto.getDeliveryCountry())
                .build();

        return Order.builder()
                .user(null)
                .totalAmount(null) // do obliczenia
                .status(null) // od ustawienia
                .paymentMethod(reqDto.getPaymentMethod())
                .shippingDate(null) // do uzupełnienia później
                .deliveryDate(null) // do uzupełnienia później
                .items(null) // do ustawienia
                .deliveryAddress(address)
                .build();
    }

    public static OrderResponseDto toDto(Order order) {
        if (order == null) {
            return null;
        }

        List<OrderItemResponseDto> orderItemResponseDtos = order.getItems().stream().map(OrderItemMapper::toDto).toList();


        OrderResponseDto resDto = OrderResponseDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .deliveryDate(order.getDeliveryDate())
                .customerId(order.getUser() != null ? order.getUser().getId() : null)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .items(orderItemResponseDtos)
                .build();


            if(order.getDeliveryAddress() != null) {
                resDto.setDeliveryStreet(order.getDeliveryAddress().getStreet());
                resDto.setDeliveryBuilding(order.getDeliveryAddress().getBuilding());
                resDto.setDeliveryApartment(order.getDeliveryAddress().getApartment());
                resDto.setDeliveryZipCode(order.getDeliveryAddress().getZipCode());
                resDto.setDeliveryCity(order.getDeliveryAddress().getCity());
                resDto.setDeliveryCountry(order.getDeliveryAddress().getCountry());
            }

        return resDto;
    }
}
