package webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webstore.domain.Customer;
import webstore.repository.CustomerRepository;
import webstore.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customerRepository.getCustomer(customerId);
    }

    @Override
    public Boolean isCustomerExist(String customerId) {
        return customerRepository.isCustomerExist(customerId);
    }
}
