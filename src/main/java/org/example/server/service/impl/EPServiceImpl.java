package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.enterprise.EnterprisePointColdWarehouse;
import org.example.server.model.enterprise.EnterprisePointWarehouse;
import org.example.server.model.entities.Worker;
import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Ingredient;
import org.example.server.model.potions.Potion;
import org.example.server.repository.EPColdWarehouseRepository;
import org.example.server.repository.EPRepository;
import org.example.server.repository.EPWarehouseRepository;
import org.example.server.repository.EnterpriseDeliveryRepository;
import org.example.server.service.EPService;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        return epWarehouseRepository.findAllByEnterprisePoint_Id(ep.getId());
    }

    @Override
    public List<EnterprisePointColdWarehouse> getEPColdWarehouseItemsByEP(EnterprisePoint ep) {
        return epColdWarehouseRepository.findAllByEnterprisePoint_IdEquals(ep.getId());
    }

    @Override
    public void createDelivery(EnterprisePoint from, EnterprisePoint to, Worker courier, List<Ingredient> ingredients, List<Integer> amounts) {
        List<Integer> ids = ingredients.stream().map(Ingredient::getId).toList();
        enterpriseDeliveryRepository.createIngredientsDelivery(from.getId(), to.getId(), courier.getId(), ids, amounts);
    }

    @Override
    public Page<EnterprisePointColdWarehouse> findPaginatedEPCW(Pageable pageable, EnterprisePoint ep) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<EnterprisePointColdWarehouse> list;
        List<EnterprisePointColdWarehouse> allEPCW = getEPColdWarehouseItemsByEP(ep);

        if (allEPCW.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allEPCW.size());
            list = allEPCW.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allEPCW.size());
    }

    @Override
    public Page<EnterprisePointWarehouse> findPaginatedEPW(Pageable pageable, EnterprisePoint ep) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<EnterprisePointWarehouse> list;
        List<EnterprisePointWarehouse> allOrders = getEPWarehouseItemsByEP(ep);

        if (allOrders.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, allOrders.size());
            list = allOrders.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), allOrders.size());
    }

    @Override
    public void addPotionToColdWarehouse(Potion potion, EnterprisePoint ep) {
        List<EnterprisePointColdWarehouse> epcwList = epColdWarehouseRepository.findAllByEnterprisePoint_IdEquals(ep.getId());
        if (epcwList.size() == 0) {
            EnterprisePointColdWarehouse epcw = new EnterprisePointColdWarehouse();
            epcw.setPotion(potion);
            epcw.setAmountOfPotions(1);
            epcw.setEnterprisePoint(ep);
            epColdWarehouseRepository.saveAndFlush(epcw);
            return;
        }

        EnterprisePointColdWarehouse epcw = epcwList.get(0);
        epcw.setAmountOfPotions(epcw.getAmountOfPotions() + 1);
        epColdWarehouseRepository.saveAndFlush(epcw);
    }
}
