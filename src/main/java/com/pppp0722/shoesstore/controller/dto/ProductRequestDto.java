package com.pppp0722.shoesstore.controller.dto;

import com.pppp0722.shoesstore.model.Category;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    private String name;
    private Category category;
    private String brand;
    private long price;
    private String description;
}
