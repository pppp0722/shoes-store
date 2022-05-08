package com.pppp0722.shoesstore.service;

import static com.pppp0722.shoesstore.model.OrderStatus.ACCEPTED;

import com.pppp0722.shoesstore.controller.dto.OrderItemRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderItemResponseDto;
import com.pppp0722.shoesstore.controller.dto.OrderRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderResponseDto;
import com.pppp0722.shoesstore.model.Order;
import com.pppp0722.shoesstore.model.OrderItem;
import com.pppp0722.shoesstore.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderDefaultService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
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

    @Override
    public List<OrderResponseDto> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email).stream()
            .map(OrderResponseDto::from)
            .toList();
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemsByOrderId(UUID orderId) {
        return orderRepository.findItemsById(orderId).stream()
            .map(OrderItemResponseDto::from)
            .toList();
    }
}
