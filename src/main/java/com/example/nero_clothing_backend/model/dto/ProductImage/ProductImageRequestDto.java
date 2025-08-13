package com.example.nero_clothing_backend.model.dto.ProductImage;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageRequestDto {

    @NotNull
//    @URL(protocol = "https", message = "Invalid URL format.")
    private String imageUrl;

    @NotNull
    @Size(min = 3, max = 100, message = "Alt text must be between 3 and 100 characters.")
    private String altText;

    @NotNull
    @Positive
    private Long productId;

}
