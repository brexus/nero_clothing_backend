package com.example.nero_clothing_backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_variant_id", referencedColumnName = "id")
    private ProductVariant productVariant;

    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice; // quantity * unitPrice

}