package com.example.nero_clothing_backend.repository;

import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.entity.ProductVariant;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;
import com.example.nero_clothing_backend.model.enums.ProductSizeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(CategoryEnum category, Pageable pageable);

    @Query("SELECT pv FROM ProductVariant pv WHERE pv.product.id = :productId AND pv.size = :size")
    ProductVariant findProductVariantByProductIdAndSize(@Param("productId") Long productId, @Param("size") ProductSizeEnum size);

}
