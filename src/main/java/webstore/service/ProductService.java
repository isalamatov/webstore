package webstore.service;

import webstore.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(String productID);

    Object getProductsByCategory(String productCategory);

    Set<Product> getProductsByFilter(Map<String,List<String>> filterParams);
}
