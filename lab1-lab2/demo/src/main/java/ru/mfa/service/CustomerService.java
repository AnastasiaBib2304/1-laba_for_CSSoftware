package ru.mfa.service;

import org.springframework.stereotype.Service;
import ru.mfa.model.Customer;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {
    private final Map<Long, Customer> customers = new HashMap<>();
    private long nextId = 1L;

    public Customer addCustomer(Customer customer) {
        long id = nextId++;
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }

    public Customer getCustomer(long id) {
        return customers.getOrDefault(id, null);
    }

    public void removeCustomer(long id) {
        customers.remove(id);
    }

    public Customer updateCustomer(long id, Customer updatedCustomer) {
        if(customers.containsKey(id)) {
            updatedCustomer.setId(id);
            customers.put(id, updatedCustomer);
            return updatedCustomer;
        }
        return null;
    }
}