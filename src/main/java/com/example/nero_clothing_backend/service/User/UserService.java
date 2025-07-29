package com.example.nero_clothing_backend.service.User;

import com.example.nero_clothing_backend.model.dto.User.UserPatchDto;
import com.example.nero_clothing_backend.model.dto.User.UserRequestDto;
import com.example.nero_clothing_backend.model.dto.User.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto reqDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto updatePartialUser(Long id, UserPatchDto reqDto);
    void deleteUser(Long id);


}
