package com.example.nero_clothing_backend.model.dto.Order;

import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemRequestDto;
import com.example.nero_clothing_backend.model.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.nero_clothing_backend.common.RegexConstants.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto {

    private String paymentMethod;

    @Positive(message = "Customer ID must be a positive number.")
    @NotNull(message = "Customer ID is required.")
    private Long userId;

    @Pattern(regexp = STREET, message = "Incorrect street name.")
    private String deliveryStreet;

    @Pattern(regexp = BUILDING, message = "Incorrect building number.")
    private String deliveryBuilding;

    @Pattern(regexp = APARTMENT, message = "Incorrect apartment number. ")
    private String deliveryApartment;

    @Pattern(regexp = ZIP_CODE, message = "Incorrect postal code format (f.e. 00-001).")
    private String deliveryZipCode;

    @Pattern(regexp = CITY, message = "Incorrect city name.")
    private String deliveryCity;

    @Pattern(regexp = COUNTRY, message = "Incorrect country name.")
    private String deliveryCountry;


    private List<OrderItemRequestDto> items;

}
