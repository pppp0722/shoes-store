package com.pppp0722.shoesstore.service;

import com.pppp0722.shoesstore.model.Category;
import com.pppp0722.shoesstore.model.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(String name, Category category, String brand, long price, String description);

    List<Product> getProductsByBrand(String brand);
}
