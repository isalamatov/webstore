package webstore.repository;

import webstore.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository {
    List<Product> getAllProducts();

    Product getProductById(String productID);

    List<Product> getProductByCategory(String productCategory);

    Set<Product> getProductsByFilter(Map<String,List<String>> filterParams);
}
