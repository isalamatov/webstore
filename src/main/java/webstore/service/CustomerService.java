package webstore.service;

import webstore.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer getCustomer(String customerId);
    Boolean isCustomerExist(String customerId);
}
