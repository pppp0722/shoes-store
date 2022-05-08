package com.pppp0722.shoesstore.controller.dto;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class OrderRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞춰 입력해주세요.")
    private String email;

    @NotBlank(message = "주소를 입력해주세요.")
    @Size(max = 200, message = "주소를 200자 이하로 입력해주세요.")
    private String address;

    @NotBlank(message = "우편번호를 입력해주세요.")
    @Size(max = 200, message = "우편번호를 200자 이하로 입력해주세요.")
    private String postcode;

    @NotNull(message = "주문할 항목이 존재하지 않습니다.")
    private List<OrderItemRequestDto> orderItems;
}
