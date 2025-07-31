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
@Table(name = "adress") // tutaj tez dawaj nazwe tabeli
public class Address extends BaseModel {

    //daj wszedzie name=
    @Column(name = "street",nullable = false)
    private String street;

    @Column(name="building",nullable = false)
    private String building;

    @Column(name = "apartment")
    private String apartment;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    // przekmin czy potrzebne bedzie, czy moze nie przechowywac samego id
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}