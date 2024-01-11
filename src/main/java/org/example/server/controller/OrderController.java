package org.example.server.controller;

import lombok.RequiredArgsConstructor;
import org.example.server.model.Cart;
import org.example.server.model.CartItem;
import org.example.server.model.OrderNumberForm;
import org.example.server.model.logistics.OrderStatus;
import org.example.server.model.potions.Potion;
import org.example.server.service.OrderService;
import org.example.server.service.PotionsIngredientsService;
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

    private final Cart cart;
    private final OrderService orderService;
    private final PotionsIngredientsService potionsIngredientsService;

    @PostMapping("/createOrder")
    String createOrderForm(@ModelAttribute Integer userId, Model model) {
        Integer orderId =  orderService.createOrder(userId, cart.getItems());
        model.addAttribute("orderId", orderId);
        return "user/orderCreateComplete";
    }

    @PostMapping("/checkStatus")
    String checkStatus(@ModelAttribute OrderNumberForm orderNumberForm, Model model) {
        OrderStatus status = orderService.takeOrderStatus(orderNumberForm.getOrderId());
        model.addAttribute("status", status.getStatus());
        return "user/checkStatusPage";
    }

    @PostMapping("/addItemToCart")
    String addPotionToCart(@ModelAttribute CartItem item, Model model) {
        cart.addItem(item);

        model.addAttribute("cart", cart);
        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

    @PostMapping("/removeCartItem")
    String removeCartItem(@ModelAttribute CartItem item, Model model) {
        cart.removeItem(item.getPotion().getId());

        model.addAttribute("cart", cart);
        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }


    @PostMapping("/addPotionToCart")
    String addPotionToCart(@ModelAttribute Potion potion, Model model) {
        cart.addItem(potion);
        model.addAttribute("cart", cart);
        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

    @GetMapping("/checkStatusPage")
    String getCheckStatusPage(Model model) {
        model.addAttribute("orderNumberForm", new OrderNumberForm());
        return "user/checkStatusPage";
    }

    @GetMapping("/createFormPage")
    String getCreateFormPage(Model model) {
        if (!model.containsAttribute("cart"))
            model.addAttribute("cart", cart);
        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

}

