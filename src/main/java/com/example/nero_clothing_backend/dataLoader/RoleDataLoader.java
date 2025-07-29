package com.example.nero_clothing_backend.dataLoader;

import com.example.nero_clothing_backend.model.entity.Role;
import com.example.nero_clothing_backend.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleDataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                    Role.builder().name("USER").build(),
                    Role.builder().name("ADMIN").build()
            ));
        }
    }
}
