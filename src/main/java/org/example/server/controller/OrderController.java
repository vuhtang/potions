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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@SessionAttributes({"cart","allPotions"})
@RequestMapping("/orders")
public class OrderController {

//    private final Cart cart;
    private final OrderService orderService;
    private final PotionsIngredientsService potionsIngredientsService;

    @PostMapping("/createOrder")
    String createOrderForm(@ModelAttribute Integer userId, @ModelAttribute Cart cart, Model model) {
        Integer orderId = orderService.createOrder(userId, cart.getItems());
        model.addAttribute("orderId", orderId);
        return "user/orderCreateComplete";
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
        Cart cart = new Cart();
        model.addAttribute("cart", cart);
        CountChangerForm countChangerForm = new CountChangerForm();
        model.addAttribute("countChangerForm", countChangerForm);
        List<Potion> allPotions = potionsIngredientsService.getAllPotions();
        model.addAttribute("allPotions", allPotions);
        return "user/orderFormPage";
    }

    @PostMapping("/removeCartItem")
    String removeCartItem(@ModelAttribute CountChangerForm countChangerForm, @ModelAttribute Cart cart, Model model) {
        cart.deleteItem(countChangerForm.getItem());
        return "user/orderFormPage";
    }


    @PostMapping("/addPotionToCart")
    String addPotionToCart(@ModelAttribute CountChangerForm countChangerForm, @ModelAttribute Cart cart, Model model) {
        CartItem item = countChangerForm.getItem();
        item.setCount(1);
        cart.addItem(item);
        cart.addItem(item);
        return "user/orderFormPage";
    }

    @PostMapping("/incCount")
    String incCount(@ModelAttribute CountChangerForm countChangerForm, @ModelAttribute Cart cart, Model model){
        cart.incCount(countChangerForm.getItem());
        return "user/orderFormPage";
    }

    @PostMapping("/decCount")
    String decCount(@ModelAttribute CountChangerForm countChangerForm, @ModelAttribute Cart cart, Model model){
        CartItem item = countChangerForm.getItem();
        cart.decCount(item);
        if(cart.checkCountEqualNol(item)){
            cart.deleteItem(item);
        }
        return "user/orderFormPage";
    }

}

