package com.example.nero_clothing_backend.model.dto.User;

import com.example.nero_clothing_backend.model.entity.Address;
import com.example.nero_clothing_backend.model.entity.Role;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String firstName;
    private String lastName;
    private String email;
    private String password; // !!!!!!! :D
    private String phoneNumber;
    private Long roleId;
    private List<Address> addressList;

}
