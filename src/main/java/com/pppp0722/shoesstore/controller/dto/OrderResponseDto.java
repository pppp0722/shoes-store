package com.pppp0722.shoesstore.controller.dto;

import com.pppp0722.shoesstore.model.Order;
import com.pppp0722.shoesstore.model.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {

    private UUID orderId;
    private String email;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private List<OrderItemResponseDto> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OrderResponseDto from(Order order) {
        return new OrderResponseDto(order.getOrderId(), order.getEmail(), order.getAddress(),
            order.getPostcode(), order.getOrderStatus(),
            order.getOrderItems().stream().map(OrderItemResponseDto::from).toList(),
            order.getCreatedAt(), order.getUpdatedAt());
    }
}
