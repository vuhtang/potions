package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.CartItem;
import org.example.server.model.logistics.Order;
import org.example.server.model.logistics.OrderStatus;
import org.example.server.model.potions.Potion;
import org.example.server.repository.OrderRepository;
import org.example.server.service.OrderService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Primary
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public boolean checkOrderStatus(int orderId, OrderStatus status) {
        return false;
    }

    @Override
    public OrderStatus takeOrderStatus(int orderId) {
        Order order = getOrderById(orderId);
        if (order == null) return null;
        return order.getStatus();
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Integer createOrder(Integer userId, List<CartItem> cartItems) {
        List<Potion> potions = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        for (CartItem item: cartItems) {
            potions.add(item.getPotion());
            amounts.add(item.getCount());
        }
        return orderRepository.createOrder(userId, potions, amounts);
    }
}
