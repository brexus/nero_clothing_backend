package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.model.dto.User.UserRequestDto;
import com.example.nero_clothing_backend.model.dto.User.UserResponseDto;
import com.example.nero_clothing_backend.model.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDto reqDto) {
        if (reqDto == null) return null;

        return User.builder()
                .firstName(reqDto.getFirstName())
                .lastName(reqDto.getLastName())
                .email(reqDto.getEmail())
                .phoneNumber(reqDto.getPhoneNumber())
                .password(reqDto.getPassword())
                .role(null)
                .build();
    }

    public static UserResponseDto toDto(User user) {
        if (user == null) return null;

        return UserResponseDto.builder()
                .id(user.getId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword()) // OJ hasla to nie chcemy dawac xd
                .phoneNumber(user.getPhoneNumber())
                .roleId(user.getRole() != null ? user.getRole().getId() : null)
                .addressList(user.getAddressList() != null ? user.getAddressList() : null)
                .build();
    }
}
