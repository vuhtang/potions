package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.logistics.Order;
import org.example.server.model.logistics.OrderStatus;
import org.example.server.repository.OrderRepository;
import org.example.server.service.OrderService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
}