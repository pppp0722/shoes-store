package com.pppp0722.shoesstore.service;

import com.pppp0722.shoesstore.controller.dto.ProductRequestDto;
import com.pppp0722.shoesstore.controller.dto.ProductResponseDto;
import com.pppp0722.shoesstore.model.Product;
import com.pppp0722.shoesstore.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ProductDefaultService implements ProductService {

    private final ProductRepository productRepository;

    public ProductDefaultService(
        ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductResponseDto createProduct(ProductRequestDto productDto) {
        LocalDateTime now = LocalDateTime.now();

        return ProductResponseDto.from(productRepository.insert(
            new Product(UUID.randomUUID(), productDto.getName(), productDto.getCategory(),
                productDto.getBrand(), productDto.getPrice(), productDto.getDescription(),
                now, now)));
    }

    @Override
    public List<ProductResponseDto> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand).stream().map(ProductResponseDto::from).toList();
    }
}
