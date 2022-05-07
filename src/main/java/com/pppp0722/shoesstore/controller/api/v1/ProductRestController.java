package com.pppp0722.shoesstore.controller.api.v1;

import com.pppp0722.shoesstore.controller.dto.ProductRequestDto;
import com.pppp0722.shoesstore.controller.dto.ProductResponseDto;
import com.pppp0722.shoesstore.model.Product;
import com.pppp0722.shoesstore.service.ProductService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<ProductResponseDto>> getProductsByBrand(@RequestParam(name = "brand") String brand) {
        return ResponseEntity.ok().body(
            productService.getProductsByBrand(brand));
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productDto) {
        return ResponseEntity.ok().body(
            productService.createProduct(productDto));
    }
}
