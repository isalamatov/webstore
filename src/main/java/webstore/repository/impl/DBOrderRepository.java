package webstore.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import webstore.domain.Order;
import webstore.repository.OrderRepository;
import webstore.repository.ProductRepository;

import java.sql.Date;
import java.sql.PreparedStatement;

@Repository
@Primary
public class DBOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    private final ProductRepository productRepository;

    @Autowired
    public DBOrderRepository(JdbcTemplate jdbcTemplate, ProductRepository productRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.productRepository = productRepository;
    }
    @Override
    public Long saveOrder(Order order) {
        String sqlSaveAddress = "INSERT INTO addresses (door_no, street_name, area_name, state, country, zip_code) " +
                "VALUES (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlSaveAddress, new String[]{"adress_id"});
            stmt.setString(1, order.getShippingDetail().getShippingAddress().getDoorNo());
            stmt.setString(2, order.getShippingDetail().getShippingAddress().getStreetName());
            stmt.setString(3, order.getShippingDetail().getShippingAddress().getAreaName());
            stmt.setString(4, order.getShippingDetail().getShippingAddress().getState());
            stmt.setString(5, order.getShippingDetail().getShippingAddress().getCountry());
            stmt.setString(6, order.getShippingDetail().getShippingAddress().getZipCode());
            return stmt;
        }, keyHolder);
        int shippingAddressId = keyHolder.getKey().intValue();
        String sqlSaveShippingDetail = "INSERT INTO shipping_details (name, shipping_date, shipping_address_id) " +
                "VALUES (?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlSaveShippingDetail, new String[]{"shipping_detail_id"});
            stmt.setString(1, order.getShippingDetail().getName());
            stmt.setString(2, order.getShippingDetail().getShippingDate());
            stmt.setInt(3, shippingAddressId);
            return stmt;
        }, keyHolder);
        int shippingDetailId = keyHolder.getKey().intValue();
        String sqlSaveOrder = "INSERT INTO orders (cart_id, customer_id, shipping_detail_id) VALUES (?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlSaveOrder, new String[]{"order_id"});
            stmt.setString(1, order.getCart().getCartId());
            stmt.setString(2, order.getCustomer().getCustomerId());
            stmt.setInt(3, shippingDetailId);
            return stmt;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
