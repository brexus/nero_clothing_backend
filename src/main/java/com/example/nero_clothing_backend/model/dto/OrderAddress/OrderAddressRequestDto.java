package com.example.nero_clothing_backend.model.dto.OrderAddress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import static com.example.nero_clothing_backend.common.RegexConstants.*;

@Data
@Builder
public class OrderAddressRequestDto {

    @NotNull(message = "Street is required.")
    @NotBlank(message = "Street cannot be blank.")
    @Size(min = 2, max = 60, message = "Street must contain between 2 and 60 characters.")
    @Pattern(regexp = STREET, message = "Incorrect street name.")
    private String street;

    @NotNull(message = "Building is required.")
    @NotBlank(message = "Building cannot be blank.")
    @Size(min = 1, max = 10, message = "Building number must contain between 1 and 10 characters.")
    @Pattern(regexp = BUILDING, message = "Incorrect building number.")
    private String building;

    @Pattern(regexp = APARTMENT, message = "Incorrect apartment number. ")
    private String apartment;

    @NotNull(message = "Zip code is required.")
    @NotBlank(message = "Zip code cannot be blank.")
    @Pattern(regexp = ZIP_CODE, message = "Incorrect postal code format (e.g. 00-001).")
    private String zipCode;

    @NotNull(message = "City required.")
    @NotBlank(message = "City cannot be blank.")
    @Size(min = 2, max = 50, message = "City must contain between 2 and 50 characters.")
    @Pattern(regexp = CITY, message = "Incorrect city name.")
    private String city;

    @NotNull(message = "Country required.")
    @NotBlank(message = "Country cannot be blank.")
    @Size(min = 2, max = 50, message = "Country must contain between 2 and 50 characters.")
    @Pattern(regexp = COUNTRY, message = "Incorrect country name.")
    private String country;
}
