package com.example.nero_clothing_backend.model.entity;

import com.example.nero_clothing_backend.model.enums.CategoryEnum;
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
public class Product extends BaseModel {

    private String name;
    private String description;
    private Double price;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariant> productVariantList = new ArrayList<>();


}
