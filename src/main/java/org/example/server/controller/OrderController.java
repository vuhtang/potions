package org.example.server.controller;

import lombok.RequiredArgsConstructor;
import org.example.server.model.Cart;
import org.example.server.model.CartItem;
import org.example.server.model.CountChangerForm;
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

//    private final Cart cart;
    private final OrderService orderService;
    private final PotionsIngredientsService potionsIngredientsService;

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

//    @PostMapping("/addItemToCart")
//    String addPotionToCart(@ModelAttribute CartItem item, Model model) {
//        cart.addItem(item);
//
//        model.addAttribute("cart", cart);
//        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
//        model.addAttribute("allPotions", allPotions);
//        return "user/orderFormPage";
//    }
//
//
//
//    @PostMapping("/addPotionToCart")
//    String addPotionToCart(@ModelAttribute Potion potion, Model model) {
//        if (cart != null)
//            cart.addItem(potion);
//        model.addAttribute("cart", cart);
//        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
//        model.addAttribute("allPotions", allPotions);
//        return "user/orderFormPage";
//    }

    @GetMapping("/checkStatusPage")
    String getCheckStatusPage(Model model) {
        model.addAttribute("orderNumberForm", new OrderNumberForm());
        return "user/checkStatusPage";
    }

    @GetMapping("/createFormPage")
    String getCreateFormPage(Model model) {
        if (!model.containsAttribute("cart"))
            model.addAttribute("cart", new Cart());
        model.addAttribute("ccForm", new CountChangerForm());
        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

    @PostMapping("/removeCartItem")
    String removeCartItem(@ModelAttribute CartItem item, Model model) {
        Cart cart = (Cart) model.getAttribute("cart");

        cart.removeItem(item.getPotion().getId());

        model.addAttribute("cart", cart);
        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

    @PostMapping("/addPotionToCart")
    public void addPotionToCart(@ModelAttribute CountChangerForm countChangerForm, Model model) {
        Cart cart = countChangerForm.getCart();
        CartItem item = countChangerForm.getItem();
        cart.addItem(item);
    }

    @PostMapping("/incCount")
    private void incCount(@ModelAttribute CountChangerForm countChangerForm, Model model){
        countChangerForm.getCart().incCount(countChangerForm.getItem());
    }

    @PostMapping("/decCount")
    private void decCount(@ModelAttribute CountChangerForm countChangerForm, Model model){
        countChangerForm.getCart().incCount(countChangerForm.getItem());
    }
}

