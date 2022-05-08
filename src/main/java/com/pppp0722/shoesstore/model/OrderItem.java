package com.pppp0722.shoesstore.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.Getter;

@Getter
public class OrderItem {

    private final UUID orderId;
    private final UUID productId;
    private long price;
    private int quantity;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderItem(UUID orderId, UUID productId, long price, int quantity,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt.truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = updatedAt.truncatedTo(ChronoUnit.MILLIS);
    }
}
