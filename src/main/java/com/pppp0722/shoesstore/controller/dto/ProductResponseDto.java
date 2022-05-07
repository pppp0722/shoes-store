package com.pppp0722.shoesstore.controller.dto;

import com.pppp0722.shoesstore.model.Category;
import com.pppp0722.shoesstore.model.Product;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseDto {

    private UUID productId;
    private String name;
    private Category category;
    private String brand;
    private long price;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static ProductResponseDto from(Product product) {
        return new ProductResponseDto(product.getProductId(), product.getName(),
            product.getCategory(), product.getBrand(), product.getPrice(),
            product.getDescription(), product.getCreatedAt(), product.getUpdatedAt());
    }
}
