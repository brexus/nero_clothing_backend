package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.model.dto.User.UserPatchDto;
import com.example.nero_clothing_backend.model.dto.User.UserRequestDto;
import com.example.nero_clothing_backend.model.dto.User.UserResponseDto;
import com.example.nero_clothing_backend.service.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto reqDto) {
        UserResponseDto resDto = userService.createUser(reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto resDto = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @GetMapping("/")
    // to pewnie planuejsz ale paginacja/sortowanie w przyszlosci
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
       List<UserResponseDto> users = userService.getAllUsers();
       return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatePartialUser(@Valid @PathVariable Long id, @RequestBody UserPatchDto reqDto) {
        UserResponseDto resDto = userService.updatePartialUser(id, reqDto);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "User with id " + id + " deleted successfully"));
    }
}
