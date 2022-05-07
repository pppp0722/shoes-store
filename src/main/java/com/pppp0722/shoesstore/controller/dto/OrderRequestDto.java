package com.pppp0722.shoesstore.controller.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class OrderRequestDto {

    private String email;
    private String address;
    private String postcode;
    private List<OrderItemRequestDto> orderItems;
}
