package org.example.server.repository;

import org.example.server.model.logistics.EnterpriseDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface EnterpriseDeliveryRepository extends JpaRepository<EnterpriseDelivery, Integer> {

    @Procedure(name = "createIngredientsDelivery")
    void createIngredientsDelivery(Integer epFromId, Integer epToId, Integer courierId,
                                   List<Integer> ingredientsIds, List<Integer> amounts);
}
