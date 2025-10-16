package com.example.nero_clothing_backend.controller;

import com.example.nero_clothing_backend.mapper.UserMapper;
import com.example.nero_clothing_backend.model.dto.User.UserPatchDto;
import com.example.nero_clothing_backend.model.dto.User.UserRequestDto;
import com.example.nero_clothing_backend.model.dto.User.UserResponseDto;
import com.example.nero_clothing_backend.model.entity.User;
import com.example.nero_clothing_backend.repository.UserRepository;
import com.example.nero_clothing_backend.service.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto reqDto) {
        UserResponseDto resDto = userService.createUser(reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resDto);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal org.springframework.security.core.userdetails.User userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono użytkownika."));

        UserResponseDto resDto = UserMapper.toDto(user);

        return ResponseEntity.ok(resDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto resDto = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(resDto);
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
