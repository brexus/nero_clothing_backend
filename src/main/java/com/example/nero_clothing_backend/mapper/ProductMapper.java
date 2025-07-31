package com.example.nero_clothing_backend.mapper;

import com.example.nero_clothing_backend.exception.CustomMessageException;
import com.example.nero_clothing_backend.model.dto.Product.ProductRequestDto;
import com.example.nero_clothing_backend.model.dto.Product.ProductResponseDto;
import com.example.nero_clothing_backend.model.entity.Product;
import com.example.nero_clothing_backend.model.enums.CategoryEnum;

import java.util.Arrays;

public class ProductMapper {
    public static Product toEntity(ProductRequestDto reqDto) {
        if (reqDto == null) {
            return null;
        }

        // w maperze imo nie powinno byc zadnej walidacji danych, powinien tylko i wylacznie mapowac
        //duzo lepiej by było zmienic w ProductRequestDto category po prostu na enuma, zamiast stringa z regexem
        // na enum i wtedy nie trzeba by było tego sprawdzac, spring sobie z tym poradzi
        // plus kwestia ze jak dodasz nowego enuma to trzeba bedzie w kilku miejscach zmieniac kod


        CategoryEnum enumCategory;
        try {
            enumCategory = CategoryEnum.valueOf(reqDto.getCategory().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new CustomMessageException("Invalid category: " + (reqDto.getCategory()));
        }

// tutaj tak samo (wsm wczesniej jak ci pisalem nie zajrzalem glebiej)
//  spring poradzi z  JSON -> Double i JSON -> Enum. Jak ktoś da złą kategorię albo zlego double to dostanie Bad Request automatycznie.
        Double price;
        try {
            price = Double.parseDouble(reqDto.getPrice());
        } catch (IllegalArgumentException e) {
            throw new CustomMessageException("Invalid price:  " + reqDto.getPrice());
        }

        Product product = Product.builder()
                .name(reqDto.getName())
                .description(reqDto.getDescription())
                .price(price)
                .category(enumCategory)
                .build();

        return product;
    }

    public static ProductResponseDto toDto(Product product) {
        if (product == null) {
            return null;
        }

        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

}
