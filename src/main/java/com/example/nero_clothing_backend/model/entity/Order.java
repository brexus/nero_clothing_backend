package com.example.nero_clothing_backend.model.entity;

import com.example.nero_clothing_backend.model.embedded.DeliveryAddress;
import com.example.nero_clothing_backend.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customer_order")
public class Order extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Double totalAmount;
    private OrderStatus status;
    private String paymentMethod;

    private LocalDateTime shippingDate;
    private LocalDateTime deliveryDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "delivery_street")),
            @AttributeOverride(name = "building", column = @Column(name = "delivery_building")),
            @AttributeOverride(name = "apartment", column = @Column(name = "delivery_apartment")),
            @AttributeOverride(name = "city", column = @Column(name = "delivery_city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "delivery_zip_code")),
            @AttributeOverride(name = "country", column = @Column(name = "delivery_country"))
    })
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

}