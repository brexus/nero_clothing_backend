package com.example.nero_clothing_backend.model.embedded;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class DeliveryAddressRequestDto {
    private String deliveryStreet;
    private String deliveryBuilding;
    private String deliveryApartment;
    private String deliveryZipCode;
    private String deliveryCity;
    private String deliveryCountry;
}
