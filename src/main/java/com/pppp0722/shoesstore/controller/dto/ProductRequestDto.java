package com.pppp0722.shoesstore.controller.dto;

import com.pppp0722.shoesstore.model.Category;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductRequestDto {

    @NotBlank(message = "제품의 이름을 입력해주세요.")
    @Size(max = 20, message = "제품의 이름을 20자 이하로 입력해주세요.")
    private String name;

    @NotNull(message = "카테고리를 입력해주세요.")
    private Category category;

    @NotBlank(message = "브랜드를 입력해주세요.")
    @Size(max = 50, message = "브랜드를 50자 이하로 입력해주세요.")
    private String brand;

    @NotNull(message = "가격을 입력해주세요.")
    @Digits(integer = 8, fraction = 0, message = "가격을 100,000,000원 미만으로 입력해주세요.")
    private long price;

    @NotBlank(message = "설명을 입력해주세요.")
    @Size(max = 500, message = "설명을 500자 이하로 입력해주세요.")
    private String description;
}
