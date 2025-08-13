package com.example.nero_clothing_backend.model.entity;

import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant extends BaseModel {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSizeEnum size;

    @Column(nullable = false)
    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id" ,referencedColumnName = "id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "productVariant", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}