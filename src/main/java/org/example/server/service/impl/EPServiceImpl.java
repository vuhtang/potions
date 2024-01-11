package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.enterprise.EnterprisePointColdWarehouse;
import org.example.server.model.enterprise.EnterprisePointWarehouse;
import org.example.server.model.entities.Worker;
import org.example.server.model.potions.Ingredient;
import org.example.server.repository.EPColdWarehouseRepository;
import org.example.server.repository.EPRepository;
import org.example.server.repository.EPWarehouseRepository;
import org.example.server.repository.EnterpriseDeliveryRepository;
import org.example.server.service.EPService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Primary
@Service
public class EPServiceImpl implements EPService {
    private final EPRepository epRepository;
    private final EPWarehouseRepository epWarehouseRepository;
    private final EPColdWarehouseRepository epColdWarehouseRepository;
    private final EnterpriseDeliveryRepository enterpriseDeliveryRepository;

    @Override
    public EnterprisePoint getEnterprisePointById(int idEP) {
        return epRepository.findById(idEP).orElse(null);
    }

    @Override
    public List<EnterprisePointColdWarehouse> getAllEPColdWarehouseItems() {
        return epColdWarehouseRepository.findAll();
    }

    @Override
    public List<EnterprisePointWarehouse> getAllEPWarehouseItems() {
        return epWarehouseRepository.findAll();
    }

    @Override
    public List<EnterprisePointWarehouse> getEPWarehouseItemsByEP(EnterprisePoint ep) {
        return epWarehouseRepository.findAllByEPId(ep.getId());
    }

    @Override
    public void createDelivery(EnterprisePoint from, EnterprisePoint to, Worker courier, List<Ingredient> ingredients, List<Integer> amounts) {
        List<Integer> ids = ingredients.stream().map(Ingredient::getId).toList();
        enterpriseDeliveryRepository.createIngredientsDelivery(from.getId(), to.getId(), courier.getId(), ids, amounts);
    }
}
