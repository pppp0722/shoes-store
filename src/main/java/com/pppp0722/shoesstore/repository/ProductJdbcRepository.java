package com.pppp0722.shoesstore.repository;

import static com.pppp0722.shoesstore.util.JdbcUtils.toUUID;

import com.pppp0722.shoesstore.model.Category;
import com.pppp0722.shoesstore.model.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class ProductJdbcRepository implements ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(
        NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product insert(Product product) {

        try {
            var update = jdbcTemplate.update(
                "INSERT INTO product "
                    + "VALUES(UUID_TO_BIN(:productId), :name, :category, :brand, :price, :description, :createdAt, :updatedAt)",
                toParamMap(product));

            if (update != 1) {
                RuntimeException e = new RuntimeException("Failed to insert product!");
                log.error("Failed to insert product!", e);
                throw e;
            }

            return product;
        } catch (DataAccessException e) {
            log.error("Can not connect to database server!", e);
            throw e;
        }
    }

    @Override
    public List<Product> findByBrand(String brand) {
        try {
            return jdbcTemplate.query("SELECT * FROM product WHERE brand = :brand",
                Collections.singletonMap("brand", brand), productRowMapper);
        } catch (DataAccessException e) {
            log.error("Can not connect to database server!", e);
            throw e;
        }
    }

    private final RowMapper<Product> productRowMapper = (resultSet, i) -> {
        var productId = toUUID(resultSet.getBytes("product_id"));
        var name = resultSet.getString("name");
        var category = Category.valueOf(resultSet.getString("category"));
        var brand = resultSet.getString("brand");
        var price = resultSet.getLong("price");
        var description = resultSet.getString("description");
        var createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        var updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        return new Product(productId, name, category, brand, price, description, createdAt,
            updatedAt);
    };

    private Map<String, Object> toParamMap(Product product) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("productId", product.getProductId().toString().getBytes());
        paramMap.put("name", product.getName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("brand", product.getBrand());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createdAt", product.getCreatedAt());
        paramMap.put("updatedAt", product.getUpdatedAt());

        return paramMap;
    }
}
