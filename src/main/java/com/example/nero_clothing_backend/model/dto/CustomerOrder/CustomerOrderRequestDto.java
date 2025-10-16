package com.example.nero_clothing_backend.model.dto.CustomerOrder;

import com.example.nero_clothing_backend.model.dto.OrderAddress.OrderAddressRequestDto;
import com.example.nero_clothing_backend.model.dto.OrderItem.OrderItemRequestDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.example.nero_clothing_backend.common.RegexConstants.PHONE_NUMBER_REGEX;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderRequestDto {

    @NotBlank
    private String paymentMethod;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Pattern(regexp = PHONE_NUMBER_REGEX, message = "Invalid phone number format")
    private String phoneNumber;

    private OrderAddressRequestDto shippingAddress;

    private List<OrderItemRequestDto> items;

}