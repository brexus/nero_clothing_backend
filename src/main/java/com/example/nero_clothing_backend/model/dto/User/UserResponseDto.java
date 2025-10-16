package com.example.nero_clothing_backend.model.dto.User;

import com.example.nero_clothing_backend.model.enums.RoleEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private RoleEnum role;

}
