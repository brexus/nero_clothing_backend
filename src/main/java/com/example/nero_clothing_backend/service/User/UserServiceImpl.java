package com.example.nero_clothing_backend.service.User;

import com.example.nero_clothing_backend.exception.EmailAlreadyExistsException;
import com.example.nero_clothing_backend.exception.UserNotFoundException;
import com.example.nero_clothing_backend.mapper.UserMapper;
import com.example.nero_clothing_backend.model.dto.User.UserPatchDto;
import com.example.nero_clothing_backend.model.dto.User.UserRequestDto;
import com.example.nero_clothing_backend.model.dto.User.UserResponseDto;
import com.example.nero_clothing_backend.model.entity.Role;
import com.example.nero_clothing_backend.model.entity.User;
import com.example.nero_clothing_backend.repository.RoleRepository;
import com.example.nero_clothing_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // o cos takiego bym dał może i wtedy bym nie musiał hardcodować id roli tylko po name tym ponizej
    //private static final String DEFAULT_USER_ROLE = "ROLE_USER";

    // sprobuj sb tez zrobic Config File to juz  poczytaj jak itd z chatem czy cos
    // zeby w application.properties dać np. default.user.role=ROLE_USER
    // i przy starcie apki to zaczytywac



    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto reqDto) {
        User user = UserMapper.toEntity(reqDto);

        // ogolnie komercyjnie sie używa Liquibase (w wolnej chwili se poczytaj/obejrz o tym zeby wiedziec ocb albo jakby ci sie bardzo chcialo to se mozesz next projekt z nim zrobic ale to jak chcesz) do zarządzania bazą  i wtedy można by skrypt dać że na pustej bazie będzie tworzyć role domyslne
        // albo mozesz napisac jakis skrypt plik init.sql (dac go do recources/sql/init.sql i przy starcie apki 1. sprawdzac czy dana rola istnieje) czy cos i tam dać insert do tabeli role albo z jakimis innymi insertami zeby byly na start apki

        //ale tak to na tą chwile to daj tu aby findByName(DEFAULT_USER_ROLE) i orElse( () -> new Role("ROLE_USER"))
        Role role = roleRepository.findById(1L).orElseThrow(); // id=1 -> ROLE_USER
        user.setRole(role);

        // check if email already exists
        if (userRepository.existsByEmail(reqDto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true) //ewentualnie mozna dac readonly true
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> userList = userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
// nie ma potrzeby dawac ifa, jak bedzie pusta to zwroci pusta getAllUsers2
        if (userList.isEmpty()) return List.of();

        return userList;
    }

    @Override
    @Transactional(readOnly = true) //ewentualnie mozna dac
    public List<UserResponseDto> getAllUsers2() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updatePartialUser(Long id, UserPatchDto reqDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (reqDto.getFirstName() != null)
            user.setFirstName(reqDto.getFirstName());

        if (reqDto.getLastName() != null)
            user.setLastName(reqDto.getLastName());

        if (reqDto.getEmail() != null)
            // dalbym sprawdzenie czy email juz istnieje
            user.setEmail(reqDto.getEmail());

        if (reqDto.getPassword() != null)
            user.setPassword(reqDto.getPassword()); // jak bedziesz logowanie/rejestracje robic nie zapominij o haszowaniu

        if (reqDto.getPhoneNumber() != null) {
            user.setPhoneNumber(reqDto.getPhoneNumber());
        }

        if (reqDto.getRoleId() != null) {
            // mozna dac swojego exceptiona, zeby wiedziec ocb
            Role reqRole = roleRepository.findById(reqDto.getRoleId()).orElseThrow(() -> new RoleNotFoundException(reqDto.getRoleId()));
            user.setRole(reqRole);
        }

        User updatedUser = userRepository.save(user);
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
// kwestia tego soft delete zamiast usuwania z bazy ale to jak chcesz
        userRepository.delete(user);
    }
}
