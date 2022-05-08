package com.pppp0722.shoesstore.repository;

import static com.pppp0722.shoesstore.util.JdbcUtils.toUUID;

import com.pppp0722.shoesstore.model.Order;
import com.pppp0722.shoesstore.model.OrderItem;
import com.pppp0722.shoesstore.model.OrderStatus;
import com.pppp0722.shoesstore.repository.exception.JdbcEmptyResultException;
import com.pppp0722.shoesstore.repository.exception.JdbcUpdateException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Order insert(Order order) {
        try {
            int orderUpdate = jdbcTemplate.update(
                "INSERT INTO orders VALUES(UUID_TO_BIN(:orderId), :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)",
                toOrderParamMap(order));

            if (orderUpdate != 1) {
                JdbcUpdateException e = new JdbcUpdateException("Failed to insert into order!");
                log.error("Failed to insert into order!", e);
                throw e;
            }

            order.getOrderItems()
                .forEach(orderItem -> {
                    int orderItemUpdate = jdbcTemplate.update(
                        "INSERT INTO order_item(order_id, product_id, price, quantity, created_at, updated_at) "
                            +
                            "VALUES(UUID_TO_BIN(:orderId), UUID_TO_BIN(:productId), :price, :quantity, :createdAt, :updatedAt)",
                        toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(),
                            order.getUpdatedAt(),
                            orderItem));

                    if (orderItemUpdate != 1) {
                        JdbcUpdateException e = new JdbcUpdateException(
                            "Failed to insert into orderItem!");
                        log.error("Failed to insert into orderItem!", e);
                        throw e;
                    }
                });

            log.info("Inserting order & order items success.");
            return order;
        } catch (DataAccessException e) {
            log.error("An error occurred in the DB!", e);
            throw e;
        }
    }

    @Override
    public List<Order> findByEmail(String email) {
        try {
            List<Order> orders = jdbcTemplate.query("SELECT * FROM orders WHERE email = :email",
                Collections.singletonMap("email", email), orderRowMapper);
            log.info("Finding orders by email success.");
            if (orders.isEmpty()) {
                JdbcEmptyResultException e = new JdbcEmptyResultException(
                    "요청하신 데이터가 존재하지 않습니다!");
                log.error("The result does not exist.", e);
                throw e;
            }
            return orders;
        } catch (DataAccessException e) {
            log.error("An error occurred in the DB!", e);
            throw e;
        }
    }

    @Override
    public List<OrderItem> findItemsById(UUID orderId) {
        try {
            List<OrderItem> orderItems = jdbcTemplate.query(
                "SELECT * FROM order_item WHERE order_id = UUID_TO_BIN(:orderId)",
                Collections.singletonMap("orderId", orderId.toString().getBytes()),
                orderItemRowMapper);
            log.info("Finding order items by orderId success.");

            if (orderItems.isEmpty()) {
                JdbcEmptyResultException e = new JdbcEmptyResultException(
                    "요청하신 데이터가 존재하지 않습니다!");
                log.error("The result does not exist.", e);
                throw e;
            }

            return orderItems;
        } catch (DataAccessException e) {
            log.error("An error occurred in the DB!", e);
            throw e;
        }
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

    private final RowMapper<Order> orderRowMapper = (resultSet, i) -> {
        var orderId = toUUID(resultSet.getBytes("order_id"));
        var email = resultSet.getString("email");
        var address = resultSet.getString("address");
        var postcode = resultSet.getString("postcode");
        var orderStatus = OrderStatus.valueOf(resultSet.getString("order_status"));
        var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        var updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return new Order(orderId, email, address, postcode, new ArrayList<OrderItem>(), orderStatus,
            createdAt, updatedAt);
    };

    private final RowMapper<OrderItem> orderItemRowMapper = (resultSet, i) -> {
        var orderId = toUUID(resultSet.getBytes("order_id"));
        var productId = toUUID(resultSet.getBytes("product_id"));
        var price = resultSet.getLong("price");
        var quantity = resultSet.getInt("quantity");
        var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        var updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return new OrderItem(orderId, productId, price, quantity, createdAt, updatedAt);
    };
}