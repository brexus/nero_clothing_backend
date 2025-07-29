package com.example.nero_clothing_backend.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

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

//    @Column(unique = true)
    private String phoneNumber;

    private String password;

    //private String role; // e.g., "customer", "admin"

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Address> addressList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;



}