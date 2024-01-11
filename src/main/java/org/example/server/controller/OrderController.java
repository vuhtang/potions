package org.example.server.controller;

import lombok.RequiredArgsConstructor;
import org.example.server.model.Cart;
import org.example.server.model.CartItem;
import org.example.server.model.OrderNumberForm;
import org.example.server.model.entities.Customer;
import org.example.server.model.logistics.OrderStatus;
import org.example.server.model.potions.Potion;
import org.example.server.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/createOrder")
    public void createOrderForm(String userName, List<CartItem> cartInput, Model model) {

        model.addAttribute("status", "Готов");
    }

    @PostMapping("/checkStatus")
    String checkStatus(@ModelAttribute OrderNumberForm orderNumberForm, Model model) {
        OrderStatus status = orderService.takeOrderStatus(orderNumberForm.getOrderId());
        model.addAttribute("status", status.getStatus());
        return "user/checkStatusPage";
    }

    @GetMapping("/checkStatusPage")
    String getCheckStatusPage(Model model) {
        model.addAttribute("orderNumberForm", new OrderNumberForm());
        return "user/checkStatusPage";
    }

    @GetMapping("/createFormPage")
    String getCreateFormPage(Model model) {
        Cart cart = new Cart();
        //todo достать список всех зелей
        List<Potion> allPotions = potionsController
        int allPotions = 5; //просто заглушка должно быть: List<Potion> allPorions
        model.addAttribute("cart", cart);
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

}

