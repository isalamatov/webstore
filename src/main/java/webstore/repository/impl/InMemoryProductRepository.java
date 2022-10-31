package webstore.repository.impl;

import org.springframework.stereotype.Repository;
import webstore.exception.ProductNotFoundException;
import webstore.domain.Product;
import webstore.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private List<Product> listOfProducts = new ArrayList<Product>();

    public InMemoryProductRepository() {
        Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
        iphone.setDescription("Apple iPhone 5s smartphone with 4.00- inch 640x1136 display and 8-megapixel rear camera");
        iphone.setCategory("Smart Phone");
        iphone.setManufacturer("Apple");
        iphone.setUnitsInStock(1000);
        Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
        laptop_dell.setDescription("Dell Inspiron 14-inch Laptop (Black) with 3 rd Generation Intel Core processors ");
        laptop_dell.setCategory("Laptop");
        laptop_dell.setManufacturer("Dell");
        laptop_dell.setUnitsInStock(1000);
        Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
        tablet_Nexus.setDescription("Google Nexus 7 is the lightest 7 inch tablet With a quad - core Qualcomm Snapdragon™ S4 Pro processor");
        tablet_Nexus.setCategory("Tablet");
        tablet_Nexus.setManufacturer("Google");
        tablet_Nexus.setUnitsInStock(1000);
        listOfProducts.add(iphone);
        listOfProducts.add(laptop_dell);
        listOfProducts.add(tablet_Nexus);
    }

    @Override
    public List<Product> getAllProducts() {
        return listOfProducts;
    }

    @Override
    public Product getProductById(String productId) {
        Product productById = null;
        for (Product product : listOfProducts) {
            if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
                productById = product;
                break;
            }
        }
        if (productById == null) {
            throw new ProductNotFoundException("No products found with the product id: " + productId);
        }
        return productById;
    }

    @Override
    public Set<Product> getProductByCategory(String category) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : listOfProducts) {
            if (category.equalsIgnoreCase(product.getCategory())) {
                productsByCategory.add(product);
            }
        }
        return new HashSet<>(productsByCategory);
    }

    @Override
    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
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
        listOfProducts.add(product);
    }

    @Override
    public Set<Product> getProductsByFilterPrice(Map<String, List<String>> filterParams) {
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
        return listOfProducts;
    }
}
