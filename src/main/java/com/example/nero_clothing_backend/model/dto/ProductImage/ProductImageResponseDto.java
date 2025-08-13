package com.example.nero_clothing_backend.model.dto.ProductImage;

import com.example.nero_clothing_backend.model.entity.ProductVariant;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductImageResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String imageUrl;
    private String altText;
    private Long productId;

}
