package webstore.repository;

import webstore.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> getAllCustomers();
}
