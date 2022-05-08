package com.pppp0722.shoesstore.controller.dto;

import java.util.UUID;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class OrderItemRequestDto {

    @NotBlank(message = "제품 id를 입력해주세요.")
    @Pattern(regexp = "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$",
        message = "올바른 제품 id를 입력해주세요.")
    private UUID productId;

    @NotNull(message = "가격을 입력해주세요.")
    @Digits(integer = 8, fraction = 0, message = "가격을 100,000,000원 미만으로 입력해주세요.")
    private long price;


    @NotNull(message = "주문할 항목의 수량이 존재하지 않습니다.")
    @Digits(integer = 2, fraction = 0, message = "한 제품의 수량은 99개 이하여야 합니다.")
    private int quantity;
}