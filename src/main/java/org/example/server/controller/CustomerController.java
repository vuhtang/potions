package org.example.server.controller;

import lombok.RequiredArgsConstructor;
import org.example.server.model.entities.Customer;
import org.example.server.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    Customer getCustomerById(
            @PathVariable Long customerId
    ) {
        return customerService.getCustomerById(customerId);
    }
}
