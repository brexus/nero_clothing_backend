package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.User.UserRequestDto;
import com.example.nero_clothing_backend.model.dto.User.UserResponseDto;
import com.example.nero_clothing_backend.model.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto reqDto) {
        return User.builder()
                .firstName(reqDto.getFirstName())
                .lastName(reqDto.getLastName())
                .email(reqDto.getEmail())
                .phoneNumber(reqDto.getPhoneNumber())
                .password(reqDto.getPassword())
                .role(reqDto.getRole())
                .build();
    }

    public static UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();

    }
}
