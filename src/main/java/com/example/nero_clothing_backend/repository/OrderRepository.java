package com.example.nero_clothing_backend.repository;

import com.example.nero_clothing_backend.model.entity.Address;
import com.example.nero_clothing_backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
