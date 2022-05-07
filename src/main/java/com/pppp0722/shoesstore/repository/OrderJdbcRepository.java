package com.pppp0722.shoesstore.repository;

import com.pppp0722.shoesstore.model.Order;
import com.pppp0722.shoesstore.model.OrderItem;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(
        NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order insert(Order order) {
        // to do: 트랜잭션 처리, 예외 처리
        jdbcTemplate.update(
            "INSERT INTO orders VALUES(UUID_TO_BIN(:orderId), :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)",
            toOrderParamMap(order));

        order.getOrderItems()
            .forEach(orderItem -> jdbcTemplate.update(
                "INSERT INTO order_item(order_id, product_id, price, quantity, created_at, updated_at) " +
                    "VALUES(UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :price, :quantity, :createdAt, :updatedAt)",
                toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(),
                    orderItem)));

        return order;
    }

    private Map<String, Object> toOrderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("email", order.getEmail());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());

        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt,
        LocalDateTime updatedAt, OrderItem orderItem) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", orderId.toString().getBytes());
        paramMap.put("productId", orderItem.getProductId().toString().getBytes());
        paramMap.put("price", orderItem.getPrice());
        paramMap.put("quantity", orderItem.getQuantity());
        paramMap.put("createdAt", createdAt);
        paramMap.put("updatedAt", updatedAt);

        return paramMap;
    }

//    private final RowMapper<Order> orderRowMapper = (resultSet, i) -> {
//        var productId = toUUID(resultSet.getBytes("product_id"));
//        var name = resultSet.getString("name");
//        var category = resultSet.getString("category");
//        var brand = resultSet.getString("brand");
//        var price = resultSet.getLong("price");
//        var description = resultSet.getString("description");
//        var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
//        var updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();
//
//        return new Order(productId, name, category, brand, price, description, createdAt,
//            updatedAt);
//    }
}