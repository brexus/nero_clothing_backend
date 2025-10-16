package com.example.nero_clothing_backend.model.dto.User;

import com.example.nero_clothing_backend.model.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private RoleEnum role;

}
