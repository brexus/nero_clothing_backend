package com.example.nero_clothing_backend.model.entity;

import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant extends BaseModel {

    @Enumerated(EnumType.STRING)
    private ProductSizeEnum size;

    private Integer stockQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id" ,referencedColumnName = "id")
    private Product product;
}