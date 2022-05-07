package com.pppp0722.shoesstore.service;

import com.pppp0722.shoesstore.controller.dto.ProductRequestDto;
import com.pppp0722.shoesstore.controller.dto.ProductResponseDto;
import com.pppp0722.shoesstore.model.Product;
import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto productDto);

    List<ProductResponseDto> getProductsByBrand(String brand);
}
