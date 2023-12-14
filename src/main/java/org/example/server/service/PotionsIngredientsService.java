package org.example.server.service;

import org.example.server.entities.EnterprisePoint;
import org.example.server.entities.Order;

public interface PotionsIngredientsService {

    boolean checkIngredientsForOrder(Order order);
    void createPotion(String namePotion);
    boolean requestIngredients(EnterprisePoint ep, String ingredient, int count);

}
