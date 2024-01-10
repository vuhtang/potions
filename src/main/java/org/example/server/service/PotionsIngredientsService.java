package org.example.server.service;

import org.example.server.model.EnterprisePoint;
import org.example.server.model.Order;

public interface PotionsIngredientsService {

    boolean checkIngredientsForOrder(Order order);
    void createPotion(String namePotion);
    boolean requestIngredients(EnterprisePoint ep, String ingredient, int count);

}
