package com.example.nero_clothing_backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseModel {

    private String guestEmail;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "customer")
    private java.util.List<CustomerOrder> customerOrders;
}
