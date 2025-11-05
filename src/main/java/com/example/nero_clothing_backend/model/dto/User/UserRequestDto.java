package com.example.nero_clothing_backend.model.dto.User;

import com.example.nero_clothing_backend.model.enums.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import static com.example.nero_clothing_backend.common.RegexConstants.NAME;

@Data
@Builder
public class UserRequestDto {
    @NotNull(message = "First name is required.")
    @NotBlank(message = "First name cannot be blank.")
    @Size(max = 50, message = "First name can contain max 50 characters.")
    @Pattern(regexp = NAME, message = "First name must contain only letters.")
    private String firstName;

    @NotNull(message = "Last name is required.")
    @NotBlank(message = "Last name cannot be blank.")
    @Size(max = 50, message = "Last name can contain max 50 characters.")
    @Pattern(regexp = NAME, message = "Last name must contain only letters.")
    private String lastName;

    @NotNull(message = "Email is required.")
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email format is incorrect.")
    private String email;

    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 8, max = 64, message = "Password must contain between 8 and 64 characters.")
    private String password;

    @NotNull(message = "Phone number is required.")
    @NotBlank(message = "Phone number cannot be blank.")
    @Pattern(regexp = "^[0-9]{9,15}$", message = "Phone number can contain only digits (9-15 digits).")
    private String phoneNumber;

    private RoleEnum role;

}
