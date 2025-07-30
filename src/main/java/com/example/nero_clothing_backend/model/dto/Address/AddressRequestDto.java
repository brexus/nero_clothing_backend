package com.example.nero_clothing_backend.model.dto.Address;
import static com.example.nero_clothing_backend.common.RegexConstants.*;

import com.example.nero_clothing_backend.common.RegexConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequestDto {

    @NotNull(message = "Street is required.")
    @Pattern(regexp = STREET, message = "Incorrect street name.")
    private String street;

    @NotNull(message = "Building is required.")
    @Pattern(regexp = BUILDING, message = "Incorrect building number.")
    private String building;

    @Pattern(regexp = APARTMENT, message = "Incorrect apartment number. ")
    private String apartment;

    @NotNull(message = "Zip code is required.")
    @Pattern(regexp = ZIP_CODE, message = "Incorrect postal code format (f.e. 00-001).")
    private String zipCode;

    @NotNull(message = "City is required.")
    @Pattern(regexp = CITY, message = "Incorrect city name.")
    private String city;

    @NotNull(message = "Country is required.")
    @Pattern(regexp = COUNTRY, message = "Incorrect country name.")
    private String country;

}
