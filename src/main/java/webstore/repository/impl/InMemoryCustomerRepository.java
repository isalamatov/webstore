package webstore.repository.impl;

import org.springframework.stereotype.Repository;
import webstore.model.Customer;
import webstore.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    List<Customer> customers = new ArrayList<>();

    public InMemoryCustomerRepository(){
        Customer customer = new Customer(1,"Igor","Moscow",5);
        customers.add(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }
}
