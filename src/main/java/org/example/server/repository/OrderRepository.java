package org.example.server.repository;

import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Potion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Procedure(procedureName = "createOrder")
    void createOrder(Long customerId, List<Potion> potions, List<Integer> amounts);
}
