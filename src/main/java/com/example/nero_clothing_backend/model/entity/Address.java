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

    @Column(unique = false, nullable = true)
    private String street;

    @Column(unique = false, nullable = false)
    private String building;

    @Column(unique = false, nullable = true)
    private String apartment;

    private String zipCode;
    private String city;
    private String country;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


}