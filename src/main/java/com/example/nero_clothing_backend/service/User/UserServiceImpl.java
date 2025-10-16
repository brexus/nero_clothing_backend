package com.example.nero_clothing_backend.service.User;

import com.example.nero_clothing_backend.exception.EmailAlreadyExistsException;
import com.example.nero_clothing_backend.exception.UserNotFoundException;
import com.example.nero_clothing_backend.mapper.UserMapper;
import com.example.nero_clothing_backend.model.dto.User.UserPatchDto;
import com.example.nero_clothing_backend.model.dto.User.UserRequestDto;
import com.example.nero_clothing_backend.model.dto.User.UserResponseDto;
import com.example.nero_clothing_backend.model.entity.User;
import com.example.nero_clothing_backend.model.enums.RoleEnum;
import com.example.nero_clothing_backend.repository.RoleRepository;
import com.example.nero_clothing_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto reqDto) {
        User user = UserMapper.toEntity(reqDto);

        user.setRole(RoleEnum.USER);

        if (userRepository.existsByEmail(reqDto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> userList = userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();

        if (userList.isEmpty()) {
            return List.of();
        }

        return userList;
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto updatePartialUser(Long id, UserPatchDto reqDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (reqDto.getFirstName() != null)
            user.setFirstName(reqDto.getFirstName());


        if (reqDto.getLastName() != null)
            user.setLastName(reqDto.getLastName());


        if (reqDto.getEmail() != null)
            user.setEmail(reqDto.getEmail());


        if (reqDto.getPassword() != null)
            user.setPassword(reqDto.getPassword());

        if (reqDto.getPhoneNumber() != null) {
            user.setPhoneNumber(reqDto.getPhoneNumber());
        }

        if (reqDto.getRoleId() != null) {
            user.setRole(RoleEnum.USER);
        }

        User updatedUser = userRepository.save(user);

        return UserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }

}
