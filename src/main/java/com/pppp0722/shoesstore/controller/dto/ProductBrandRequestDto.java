package com.pppp0722.shoesstore.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductBrandRequestDto {

    @NotBlank(message = "브랜드를 입력해주세요.")
    @Size(max = 50, message = "브랜드를 20자 이하로 입력해주세요.")
    private String brand;
}