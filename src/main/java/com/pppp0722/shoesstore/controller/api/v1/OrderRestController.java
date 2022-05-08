package com.pppp0722.shoesstore.controller.api.v1;

import com.pppp0722.shoesstore.controller.dto.OrderItemResponseDto;
import com.pppp0722.shoesstore.controller.dto.OrderRequestDto;
import com.pppp0722.shoesstore.controller.dto.OrderResponseDto;
import com.pppp0722.shoesstore.service.OrderService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping("/api/v1/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByEmail(
        @RequestParam String email) {
        return ResponseEntity.ok().body(
            orderService.getOrdersByEmail(email));
    }

    @PostMapping("/api/v1/orders")
    public ResponseEntity<OrderResponseDto> createOrder(
        @Valid @RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok().body(
            orderService.createOrder(orderRequestDto));
    }

    @GetMapping("/api/v1/order-items")
    public ResponseEntity<List<OrderItemResponseDto>> getOrdersByEmail(
        @RequestParam UUID orderId) {
        return ResponseEntity.ok().body(
            orderService.getOrderItemsByOrderId(orderId));
    }
}
