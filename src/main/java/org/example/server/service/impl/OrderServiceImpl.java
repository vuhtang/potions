package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.CartItem;
import org.example.server.model.logistics.Order;
import org.example.server.model.logistics.OrderStatus;
import org.example.server.model.potions.Potion;
import org.example.server.repository.OrderRepository;
import org.example.server.service.OrderService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Integer> potionIds = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        for (CartItem item: cartItems) {
            potionIds.add(item.getPotion().getId());
            amounts.add(item.getCount());
        }
        Integer[] potionsIdsArray = new Integer[potionIds.size()];
        potionIds.toArray(potionsIdsArray);
        Integer[] amountsArray = new Integer[amounts.size()];
        amounts.toArray(amountsArray);
        return orderRepository.createOrder(userId, potionsIdsArray, amountsArray);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllActiveOrders() {
        return orderRepository.findAllActiveOrders();
    }

    @Override
    public Page<Order> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Order> list;
        List<Order> allOrders = getAllOrders();

        if (allOrders.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allOrders.size());
            list = allOrders.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allOrders.size());
    }

    @Override
    public Page<Order> findPaginatedActive(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Order> list;
        List<Order> allOrders = getAllActiveOrders();

        if (allOrders.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allOrders.size());
            list = allOrders.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allOrders.size());
    }
}
