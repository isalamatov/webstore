package webstore.service;

import webstore.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(String productID);

    Set<Product> getProductsByCategory(String productCategory);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    List<Product> getProductsByManufacturer(String manufacturer);

    Set<Product> getProductsByFilterPrice(Map<String, List<String>> filterParams);

    List<Product> getProductsByCategoryAndPriceFilterAndManufacturer(String category, Map<String, List<String>> filterParams, String manufacturer);
}
