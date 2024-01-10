package org.example.server.service;

import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.logistics.Order;

public interface PotionsIngredientsService {

    boolean checkIngredientsForOrder(Order order);
    void createPotion(String namePotion);
    boolean requestIngredients(EnterprisePoint ep, String ingredient, int count);

}
