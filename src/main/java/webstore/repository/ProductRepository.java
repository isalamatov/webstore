package webstore.repository;

import webstore.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository {
    List<Product> getAllProducts();

    Product getProductById(String productID);

    Set<Product> getProductByCategory(String productCategory);

    Set<Product> getProductsByFilter(Map<String,List<String>> filterParams);

    void addProduct(Product product);

    void update(Product product);

    Set<Product> getProductsByFilterPrice(Map<String, List<String>> filterParams);

    List<Product> getProductsByManufacturer(String manufacturer);
}
