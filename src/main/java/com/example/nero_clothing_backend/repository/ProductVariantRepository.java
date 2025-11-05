package com.example.nero_clothing_backend.repository;

import com.example.nero_clothing_backend.model.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProductId(Long productId);

    @Query("SELECT pv FROM ProductVariant pv WHERE pv.id IN :productVariantIds")
    List<ProductVariant> findAllByIdIn(List<Long> productVariantIds);
}
