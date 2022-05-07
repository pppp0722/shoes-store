package com.pppp0722.shoesstore.controller.api.v1;

import com.pppp0722.shoesstore.controller.dto.OrderRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderResponseDto;
import com.pppp0722.shoesstore.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/api/v1/orders")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderDto) {
        return ResponseEntity.ok().body(
            orderService.createOrder(orderDto));
    }
}
