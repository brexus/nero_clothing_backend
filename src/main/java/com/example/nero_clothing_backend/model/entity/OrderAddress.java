package com.example.nero_clothing_backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddress extends BaseModel {

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String building;

    @Column(nullable = true)
    private String apartment;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @OneToOne(mappedBy = "shippingAddress")
    private CustomerOrder customerOrder;

}