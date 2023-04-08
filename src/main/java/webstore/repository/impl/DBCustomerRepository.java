package webstore.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import webstore.domain.Address;
import webstore.domain.Customer;
import webstore.repository.CustomerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Primary
public class DBCustomerRepository implements CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sqlQueryGetAll = "SELECT * FROM customers";
        return jdbcTemplate.query(sqlQueryGetAll, (rs, rn) -> makeCustomer(rs));
    }

    @Override
    public void saveCustomer(Customer customer) {
        String sqlQueryAddAddress = "INSERT INTO addresses (door_no, street_name, area_name, state, country, zip_code)" +
                " VALUES (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQueryAddAddress, new String[]{"adress_id"});
            stmt.setString(1, customer.getBillingAddress().getDoorNo());
            stmt.setString(2, customer.getBillingAddress().getStreetName());
            stmt.setString(3, customer.getBillingAddress().getAreaName());
            stmt.setString(4, customer.getBillingAddress().getState());
            stmt.setString(5, customer.getBillingAddress().getCountry());
            stmt.setString(6, customer.getBillingAddress().getZipCode());
            return stmt;
        }, keyHolder);
        int billingAddressId = keyHolder.getKey().intValue();
        String sqlQueryAdd = "INSERT INTO customers (customer_id, name, billing_address_id, phone_number)" +
                " VALUES (?,?,?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sqlQueryAdd, customer.getCustomerId(), customer.getName(),
                billingAddressId, customer.getPhoneNumber());
    }

    @Override
    public Customer getCustomer(String customerId) {
        String sqlQueryGetById = "SELECT * FROM customers WHERE customer_id = ?";
        return jdbcTemplate.queryForObject(sqlQueryGetById, (rs, rn) -> makeCustomer(rs), customerId);
    }

    @Override
    public Boolean isCustomerExist(String customerId) {
        String sql = "SELECT 1 FROM customers WHERE customer_id = ? LIMIT 1";
        return Boolean.TRUE.equals(jdbcTemplate.query(sql, ResultSet::next, customerId));
    }

    private Customer makeCustomer(ResultSet rs) throws SQLException {
        String customerId = rs.getString("customer_id");
        String name = rs.getString("name");
        String phoneNumber = rs.getString("phone_number");
        int billingAddressId = rs.getInt("billing_address_id");
        String sqlQueryAddress = "SELECT * FROM addresses WHERE adress_id = ?";
        Address billingAddress = jdbcTemplate.queryForObject(sqlQueryAddress, (rs2, rn) -> makeAddress(rs2), billingAddressId);
        return new Customer(customerId, name, billingAddress, phoneNumber);
    }

    private Address makeAddress(ResultSet rs) throws SQLException {
        String adressId = rs.getString("adress_id");
        String doorNo = rs.getString("door_no");
        String streetName = rs.getString("street_name");
        String areaName = rs.getString("area_name");
        String state = rs.getString("state");
        String country = rs.getString("country");
        String zipCode = rs.getString("zip_code");
        Address address = new Address();
        address.setDoorNo(doorNo);
        address.setStreetName(streetName);
        address.setAreaName(areaName);
        address.setState(state);
        address.setCountry(country);
        address.setZipCode(zipCode);
        return address;
    }
}
