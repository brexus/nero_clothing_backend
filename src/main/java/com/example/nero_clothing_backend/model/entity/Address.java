package com.example.nero_clothing_backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseModel {

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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}