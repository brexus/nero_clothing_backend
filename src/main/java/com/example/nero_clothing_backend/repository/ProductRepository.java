package com.example.nero_clothing_backend.repository;

import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(CategoryEnum category);

}
