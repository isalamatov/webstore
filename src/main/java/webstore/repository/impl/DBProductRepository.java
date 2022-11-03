package webstore.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import webstore.domain.Product;
import webstore.repository.ProductRepository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
@Primary
public class DBProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Product> getAllProducts() {
        String sqlQueryGetAll = "SELECT * FROM products";
        return jdbcTemplate.query(sqlQueryGetAll, (rs, rn) -> makeProduct(rs));
    }


    @Override
    public Product getProductById(String productID) {
        String sqlQueryGetById = "SELECT * FROM products WHERE product_id = ?";
        return jdbcTemplate.queryForObject(sqlQueryGetById, (rs, rn) -> makeProduct(rs), productID);
    }

    @Override
    public Set<Product> getProductByCategory(String productCategory) {
        String sqlQueryGetByCategory = "SELECT * FROM products WHERE category = ?";
        return new HashSet<>(jdbcTemplate.query(sqlQueryGetByCategory, (rs, rn) -> makeProduct(rs), productCategory));
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        //TODO Replace with proper SQL query
        List<Product> listOfProducts = getAllProducts();
        Set<Product> productsByBrand = new HashSet<>();
        Set<Product> productsByCategory = new HashSet<>();
        Set<String> criterias = filterParams.keySet();
        if (criterias.contains("brand")) {
            for (String brandName : filterParams.get("brand")) {
                for (Product product : listOfProducts) {
                    if (brandName.equalsIgnoreCase(product.getManufacturer())) {
                        productsByBrand.add(product);
                    }
                }
            }
        }
        if (criterias.contains("category")) {
            for (String category : filterParams.get("category")) {
                productsByCategory.addAll(this.getProductByCategory(category));
            }
        }
        productsByCategory.retainAll(productsByBrand);
        return productsByCategory;
    }

    @Override
    public void addProduct(Product product) {
        String sqlQueryAdd = "INSERT INTO products " +
                "(product_id," +
                " name," +
                " unit_price," +
                " description," +
                " manufacturer," +
                " category," +
                " units_in_stock," +
                " units_in_order," +
                " discounted," +
                " condition) VALUES (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sqlQueryAdd,
                product.getProductId(),
                product.getName(),
                product.getUnitPrice(),
                product.getDescription(),
                product.getManufacturer(),
                product.getCategory(),
                product.getUnitsInStock(),
                product.getUnitsInOrder(),
                product.isdiscounted(),
                product.getCondition());
    }

    @Override
    public Set<Product> getProductsByFilterPrice(Map<String, List<String>> filterParams) {
        //TODO Replace with proper SQL query
        List<Product> listOfProducts = getAllProducts();
        Set<Product> result = new HashSet<>();
        for (Product product : listOfProducts) {
            System.out.println(product.getUnitPrice().doubleValue());
            System.out.println(filterParams.get("low").get(0));
            System.out.println(Double.parseDouble(filterParams.get("low").get(0)));
            if ((product.getUnitPrice().doubleValue() >= Double.parseDouble(filterParams.get("low").get(0)))
                    && ((product.getUnitPrice().doubleValue() <= Double.parseDouble(filterParams.get("high").get(0))))) {
                result.add(product);
            }

        }
        return result;
    }

    @Override
    public List<Product> getProductsByManufacturer(String manufacturer) {
        String sqlQueryGetByCategory = "SELECT * FROM products WHERE manufacturer = ?";
        return jdbcTemplate.query(sqlQueryGetByCategory, (rs, rn) -> makeProduct(rs), manufacturer);    }

    private Product makeProduct(ResultSet rs) throws SQLException {
        String productId = rs.getString("product_id");
        String name = rs.getString("name");
        BigDecimal unitPrice = rs.getBigDecimal("unit_price");
        String description = rs.getString("description");
        String manufacturer = rs.getString("manufacturer");
        String category = rs.getString("category");
        long unitsInStock = rs.getLong("units_in_stock");
        long unitsInOrder = rs.getLong("units_in_order");
        boolean discounted = rs.getBoolean("discounted");
        String condition = rs.getString("condition");
        return new Product(productId, name, unitPrice, description, manufacturer, category, unitsInStock, unitsInOrder,
                discounted, condition);
    }
}
