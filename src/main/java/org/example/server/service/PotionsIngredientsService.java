package org.example.server.service;

import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Potion;

import java.util.List;

public interface PotionsIngredientsService {

    boolean checkIngredientsForOrder(Order order);
    void createPotion(String namePotion);
    boolean requestIngredients(EnterprisePoint ep, String ingredient, int count);

    List<Potion> getAllPotions();
}
