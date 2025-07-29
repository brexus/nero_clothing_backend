package com.example.nero_clothing_backend.model.dto.User;

import jakarta.validation.constraints.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
public class UserRequestDto {
    @Pattern(regexp = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s-]{3,80}$", message = "Incorrect first name.")
    private String firstName;

    @Pattern(regexp = "^[A-Za-ząĄćĆęĘłŁńŃóÓśŚżŻźŹ\\s-]{3,80}$", message = "Incorrect last name.")
    private String lastName;

//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$", message = "Incorrect email format.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Password must be at least 8 characters long and contain at least one letter and one number.")
    private String password;

    @Pattern(regexp = "^\\+([1-9][0-9]{0,2})([0-9]{6,12})$", message = "Incorrect phone number format. Phone number must start with a '+'.")
    private String phoneNumber;

    private Long roleId;

}
