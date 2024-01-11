package org.example.server.service;

import org.example.server.model.entities.Customer;

public interface CustomerService {
    Customer getCustomerById(Integer id);
}
