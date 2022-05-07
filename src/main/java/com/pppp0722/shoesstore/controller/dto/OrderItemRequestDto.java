package com.pppp0722.shoesstore.controller.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderItemRequestDto {

    private UUID productId;
    private long price;
    private int quantity;
}