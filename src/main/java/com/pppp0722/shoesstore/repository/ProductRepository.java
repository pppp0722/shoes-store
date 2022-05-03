package com.pppp0722.shoesstore.repository;

import com.pppp0722.shoesstore.model.Product;
import java.util.List;

public interface ProductRepository {

    Product insert(Product product);

    List<Product> findByBrand(String brand);
}
