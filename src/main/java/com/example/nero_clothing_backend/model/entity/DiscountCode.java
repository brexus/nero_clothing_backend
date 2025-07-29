package com.example.nero_clothing_backend.model.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCode extends BaseModel {

    private String code; // The discount code itself
    private double discountPercentage; // The percentage discount applied by the code
    private String description; // Description of the discount code
    private boolean isActive; // Indicates if the discount code is currently active
    private String expirationDate; // Expiration date of the discount code in ISO format (e.g., "2023-12-31")

    private String minimumOrderAmount; // Minimum order amount required to use the discount code
    private String usageLimit; // Maximum number of times the discount code can be used
    private String usageCount; // Number of times the discount code has been used


}