package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.Customer;
import org.example.server.repository.CustomerRepository;
import org.example.server.service.CustomerService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Primary
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customerRepository.findById(id).orElseThrow();
    }
}
