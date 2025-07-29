package com.example.nero_clothing_backend.model.dto.Address;
import static com.example.nero_clothing_backend.common.RegexConstants.*;

import com.example.nero_clothing_backend.common.RegexConstants;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressRequestDto {

    @Pattern(regexp = STREET, message = "Incorrect street name.")
    private String street;

    @Pattern(regexp = BUILDING, message = "Incorrect building number.")
    private String building;

    @Pattern(regexp = APARTMENT, message = "Incorrect apartment number. ")
    private String apartment;

    @Pattern(regexp = ZIP_CODE, message = "Incorrect postal code format (f.e. 00-001).")
    private String zipCode;

    @Pattern(regexp = CITY, message = "Incorrect city name.")
    private String city;

    @Pattern(regexp = COUNTRY, message = "Incorrect country name.")
    private String country;

}
