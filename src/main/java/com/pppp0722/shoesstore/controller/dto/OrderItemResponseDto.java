package com.pppp0722.shoesstore.controller.dto;

import com.pppp0722.shoesstore.model.OrderItem;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemResponseDto {

    private UUID orderId;
    private UUID productId;
    private long price;
    private int quantity;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static OrderItemResponseDto from(OrderItem orderItem) {
        return new OrderItemResponseDto(orderItem.getOrderId(), orderItem.getProductId(),
            orderItem.getPrice(), orderItem.getQuantity(), orderItem.getCreatedAt(),
            orderItem.getUpdatedAt());
    }
}
