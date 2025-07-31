package com.example.nero_clothing_backend.model.embedded;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable //nw co  z tym
/*
ale poczytalem troche o tym i chyba to jest ok
juz kumam ocb z tym co mowiles ze sie bedzie kopiowal i zmienial itd
 */
public class DeliveryAddress {
    private String street;
    private String building;
    private String apartment;
    private String zipCode;
    private String city;
    private String country;
}
