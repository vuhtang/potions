package org.example.server.repository;

import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Potion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Procedure(procedureName = "createOrder")
    Integer createOrder(Integer customerId, List<Potion> potions, List<Integer> amounts);

    @Query(value = "select * from orders where order_status != 3", nativeQuery = true)
    List<Order> findAllActiveOrders();
}
