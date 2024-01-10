package org.example.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class OrderController {

    @Controller
    @RequestMapping("/orders")
    public class OrderControl {

        @PostMapping("/checkStatus")
        public void checkOrderForm(@ModelAttribute int numberOrder, Model model) {

            //todo добавить get статуса по номеру заказу и возвращать строку

            model.addAttribute("status", "Готов");
        }
    }
}
