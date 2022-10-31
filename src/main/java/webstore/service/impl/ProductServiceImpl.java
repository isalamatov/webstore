package webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webstore.domain.Product;
import webstore.repository.ProductRepository;
import webstore.service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(String productID) {
        return productRepository.getProductById(productID);
    }

    @Override
    public Set<Product> getProductsByCategory(String productCategory) {
        return productRepository.getProductByCategory(productCategory);
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        return productRepository.getProductsByFilter(filterParams);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    @Override
    public List<Product> getProductsByManufacturer(String manufacturer) {
        return productRepository.getProductsByManufacturer(manufacturer);
    }

    @Override
    public Set<Product> getProductsByFilterPrice(Map<String, List<String>> filterParams) {
        return productRepository.getProductsByFilterPrice(filterParams);
    }

    @Override
    public List<Product> getProductsByCategoryAndPriceFilterAndManufacturer(String category, Map<String, List<String>> filterParams, String manufacturer) {
        Set<Product> productsInCategory = getProductsByCategory(category);
        List<Product> productsOfManufacturer = getProductsByManufacturer(manufacturer);
        Set<Product> productsOfSuitablePrice = getProductsByFilterPrice(filterParams);
        productsOfManufacturer.retainAll(productsInCategory);
        productsOfManufacturer.retainAll(productsOfSuitablePrice);
        return productsOfManufacturer;
    }
}
