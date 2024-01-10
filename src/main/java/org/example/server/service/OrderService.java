package org.example.server.service;

import org.example.server.model.Order;
import org.example.server.model.OrderStatus;

import java.util.List;

public interface OrderService {
    boolean checkOrderStatus(int orderId, OrderStatus status);
    OrderStatus takeOrderStatus(int orderId);

    Order getOrderById(int orderId);

    //получать все ордера не прикол их миллион
//    List<Order> getAllOrders();
}
