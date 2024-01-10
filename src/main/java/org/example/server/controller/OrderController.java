package org.example.server.controller;

import org.example.server.model.Cart;
import org.example.server.model.CartItem;
import org.example.server.model.entities.Customer;
import org.example.server.model.potions.Potion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @PostMapping("/createOrder")
    public void createOrderForm(String userName, List<CartItem> cartInput, Model model) {

        model.addAttribute("status", "Готов");
    }

    @PostMapping("/checkStatus")
    String checkStatus(int numberOrder, Model model) {

        //todo добавить get статуса по номеру заказу и возвращать строку

        model.addAttribute("status", numberOrder);
        return "user/checkStatusPage";
    }

    @GetMapping("/checkStatusPage")
    String getCheckStatusPage(Model model) {
        return "user/checkStatusPage";
    }

    @GetMapping("/createFormPage")
    String getCreateFormPage(Model model) {
        Cart cart = new Cart();
        //todo достать список всех зелей
        int allPotions = 5; //просто заглушка должно быть: List<Potion> allPorions
        model.addAttribute("cart", cart);
//        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

}

