package org.example.server.service;

import org.example.server.model.Customer;

public interface CustomerService {
    Customer getCustomerById(Long id);
}
