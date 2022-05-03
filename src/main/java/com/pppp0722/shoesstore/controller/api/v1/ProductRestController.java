package com.pppp0722.shoesstore.controller.api.v1;

import com.pppp0722.shoesstore.model.Category;
import com.pppp0722.shoesstore.model.Product;
import com.pppp0722.shoesstore.service.ProductService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<List<Product>> getProductsByBrand(
        @RequestParam(name = "brand") String brand) {
        return ResponseEntity.ok().body(
            productService.getProductsByBrand(brand)
        );
    }

    @PostMapping("/api/v1/products")
    public void createProduct(@RequestBody CreateProductRequest request) {
        productService.createProduct(request.name(), request.category(), request.brand(),
            request.price(), request.description());
    }

    public record CreateProductRequest(String name, Category category, String brand, long price,
                                       String description) {

    }
}
