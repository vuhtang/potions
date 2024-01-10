package org.example.server.service;

import org.example.server.model.logistics.Order;
import org.example.server.model.logistics.OrderStatus;

public interface OrderService {
    boolean checkOrderStatus(int orderId, OrderStatus status);
    OrderStatus takeOrderStatus(int orderId);

    Order getOrderById(int orderId);

    //получать все ордера не прикол их миллион
//    List<Order> getAllOrders();
}
