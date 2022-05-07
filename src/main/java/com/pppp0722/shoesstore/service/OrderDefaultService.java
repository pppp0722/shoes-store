package com.pppp0722.shoesstore.service;

import static com.pppp0722.shoesstore.model.OrderStatus.ACCEPTED;

import com.pppp0722.shoesstore.controller.dto.OrderItemRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderResponseDto;
import com.pppp0722.shoesstore.model.Order;
import com.pppp0722.shoesstore.model.OrderItem;
import com.pppp0722.shoesstore.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderDefaultService implements OrderService {

    private final OrderRepository orderRepository;

    public OrderDefaultService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderDto) {
        UUID orderId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        String email = orderDto.getEmail();
        String address = orderDto.getAddress();
        String postcode = orderDto.getPostcode();
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequestDto orderItemDto : orderDto.getOrderItems()) {
            orderItems.add(
                new OrderItem(orderId, orderItemDto.getProductId(), orderItemDto.getPrice(),
                    orderItemDto.getQuantity(), now, now));
        }

        return OrderResponseDto.from(orderRepository.insert(
            new Order(orderId, email, address, postcode, orderItems, ACCEPTED,
                now, now)));
    }
}
