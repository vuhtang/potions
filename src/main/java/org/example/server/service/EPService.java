package org.example.server.service;

import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.enterprise.EnterprisePointColdWarehouse;
import org.example.server.model.enterprise.EnterprisePointWarehouse;
import org.example.server.model.entities.Worker;
import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Ingredient;
import org.example.server.model.potions.Potion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EPService {
    EnterprisePoint getEnterprisePointById(int idEP);

    List<EnterprisePointColdWarehouse> getAllEPColdWarehouseItems();

    List<EnterprisePointWarehouse> getAllEPWarehouseItems();

    void addPotionToColdWarehouse(Potion potion, EnterprisePoint ep);

    List<EnterprisePointWarehouse> getEPWarehouseItemsByEP(EnterprisePoint ep);

    List<EnterprisePointColdWarehouse> getEPColdWarehouseItemsByEP(EnterprisePoint ep);
    Page<EnterprisePointColdWarehouse> findPaginatedEPCW(Pageable pageable, EnterprisePoint ep);
    Page<EnterprisePointWarehouse> findPaginatedEPW(Pageable pageable, EnterprisePoint ep);

    void createDelivery(EnterprisePoint from, EnterprisePoint to, Worker courier, List<Ingredient> ingredients, List<Integer> amounts);
}
