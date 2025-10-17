package com.example.nero_clothing_backend.repository;

import com.example.nero_clothing_backend.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByGuestEmail(String email);

    Customer findByGuestEmail(String email);

    Optional<Customer> findByUserId(Long userId);
}
