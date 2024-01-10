package org.example.server.controller;

import lombok.RequiredArgsConstructor;
import org.example.server.model.Customer;
import org.example.server.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final CustomerService customerService;

    @GetMapping("user/start")
    String getTestString(Model model) {
        Long id = Long.parseLong("34");
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "user/start";
    }

}
