package org.sporty.service;

import jakarta.annotation.PostConstruct;
import org.sporty.model.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    private Map<Long, Customer> customerData = new HashMap<>();

    @PostConstruct
    public void init() {
        customerData.put(1L, new Customer(1L, "John Doe", 0));
        customerData.put(2L, new Customer(2L, "Jane Smith", 8));  // Jane has some loyalty points.
    }

    public Optional<Customer> getCustomerById(Long id) {
        return Optional.ofNullable(customerData.get(id));
    }

    public void updateCustomer(Customer customer) {
        customerData.put(customer.getId(), customer);
    }
}
