package com.pppp0722.shoesstore.repository;

import com.pppp0722.shoesstore.model.Order;
import com.pppp0722.shoesstore.model.OrderItem;
import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order insert(Order order);

    List<Order> findByEmail(String email);

    List<OrderItem> findItemsById(UUID orderId);
}
