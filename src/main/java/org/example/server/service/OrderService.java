package org.example.server.service;

import org.example.server.entities.Order;
import org.example.server.entities.OrderStatus;

import java.util.List;

public interface OrderService {
    boolean checkOrderStatus(int order_id, OrderStatus status);
    OrderStatus takeOrderStatus(int order_id);
    List<Order> getAllOrders();
}