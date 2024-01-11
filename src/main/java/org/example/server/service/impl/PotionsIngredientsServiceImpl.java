package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Potion;
import org.example.server.repository.PotionsRepository;
import org.example.server.service.PotionsIngredientsService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Primary
public class PotionsIngredientsServiceImpl implements PotionsIngredientsService {

    private final PotionsRepository potionsRepository;

    @Override
    public boolean checkIngredientsForOrder(Order order) {
        return false;
    }

    @Override
    public void createPotion(String namePotion) {

    }

    @Override
    public boolean requestIngredients(EnterprisePoint ep, String ingredient, int count) {
        return false;
    }

    @Override
    public List<Potion> getAllPotions() {
        return potionsRepository.findAll();
    }
}
