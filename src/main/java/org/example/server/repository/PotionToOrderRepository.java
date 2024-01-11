package org.example.server.repository;

import org.example.server.model.logistics.Order;
import org.example.server.model.potions.OrdersToPotions;
import org.example.server.model.potions.Potion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PotionToOrderRepository extends JpaRepository<OrdersToPotions, Integer> {

    List<OrdersToPotions> findAllByOrder(Order order);
}
