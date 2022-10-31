package webstore.repository.impl;

import org.springframework.stereotype.Repository;
import webstore.domain.Customer;
import webstore.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

    List<Customer> customers = new ArrayList<>();

    public InMemoryCustomerRepository(){
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customers.stream().
                filter((x) -> x.getCustomerId().equals(customerId)).
                findAny().
                get();
    }

    @Override
    public Boolean isCustomerExist(String customerId) {
        return customers.stream().
                anyMatch(customer -> customer.getCustomerId().equals(customerId));
    }
}
