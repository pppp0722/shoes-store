package com.pppp0722.shoesstore.service;

import com.pppp0722.shoesstore.controller.dto.OrderRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderResponseDto;
import com.pppp0722.shoesstore.model.Order;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderDto);
}