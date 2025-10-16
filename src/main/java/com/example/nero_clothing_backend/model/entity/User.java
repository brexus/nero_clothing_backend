package com.example.nero_clothing_backend.model.entity;

import com.example.nero_clothing_backend.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseModel {

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

}