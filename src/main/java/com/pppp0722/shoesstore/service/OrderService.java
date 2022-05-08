package com.pppp0722.shoesstore.service;

import com.pppp0722.shoesstore.controller.dto.OrderItemResponseDto;
import com.pppp0722.shoesstore.controller.dto.OrderRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderResponseDto;
import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto orderDto);

    List<OrderResponseDto> getOrdersByEmail(String email);

    List<OrderItemResponseDto> getOrderItemsByOrderId(UUID orderId);
}