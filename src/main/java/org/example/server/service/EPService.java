package org.example.server.service;

import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.enterprise.EnterprisePointColdWarehouse;
import org.example.server.model.enterprise.EnterprisePointWarehouse;
import org.example.server.model.entities.Worker;
import org.example.server.model.potions.Ingredient;

import java.util.List;

public interface EPService {
    EnterprisePoint getEnterprisePointById(int idEP);

    List<EnterprisePointColdWarehouse> getAllEPColdWarehouseItems();

    List<EnterprisePointWarehouse> getAllEPWarehouseItems();

    List<EnterprisePointWarehouse> getEPWarehouseItemsByEP(EnterprisePoint ep);

    void createDelivery(EnterprisePoint from, EnterprisePoint to, Worker courier, List<Ingredient> ingredients, List<Integer> amounts);
}
