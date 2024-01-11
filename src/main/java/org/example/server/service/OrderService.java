package org.example.server.service;

import org.example.server.model.CartItem;
import org.example.server.model.logistics.Order;
import org.example.server.model.logistics.OrderStatus;

import java.util.List;

public interface OrderService {
    boolean checkOrderStatus(int orderId, OrderStatus status);
    OrderStatus takeOrderStatus(int orderId);

    Integer createOrder(Integer userId, List<CartItem> cartItems);
    Order getOrderById(int orderId);
}
