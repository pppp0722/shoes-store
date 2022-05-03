package com.pppp0722.shoesstore.service;

import com.pppp0722.shoesstore.model.Category;
import com.pppp0722.shoesstore.model.Product;
import com.pppp0722.shoesstore.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    public DefaultProductService(
        ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(String name, Category category, String brand, long price,
        String description) {
        return productRepository.insert(
            new Product(UUID.randomUUID(), name, category, brand, price, description,
                LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }
}
