package com.example.nero_clothing_backend.model.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DeliveryAddress {
    private String street;
    private String building;
    private String apartment;
    private String zipCode;
    private String city;
    private String country;
}
