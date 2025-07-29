package com.example.nero_clothing_backend.model.dto.Address;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressPatchDto {
    private Long id;

    @Pattern(regexp = "^[A-Za-zĄąĆćĘęŁłŃńÓóŚśŻżŹź0-9\\s\\-.]{3,100}$", message = "Incorrect street name.")
    private String street;

    @Pattern(regexp = "^[0-9]+[A-Za-z0-9/\\-]{0,5}$", message = "Incorrect building number.")
    private String building;

    @Pattern(regexp = "^[0-9]{0,5}$", message = "Incorrect apartment number. ")
    private String apartment;

    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Incorrect postal code format (f.e. 00-001).")
    private String postalCode;

    @Pattern(regexp = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s\\-]{3,80}$", message = "Incorrect city name.")
    private String city;

    @Pattern(regexp = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s\\-]{3,50}$", message = "Incorrect country name.")
    private String country;
}
