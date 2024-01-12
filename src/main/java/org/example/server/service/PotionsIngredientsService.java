package org.example.server.service;

import org.example.server.model.PotionNumberForm;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Ingredient;
import org.example.server.model.potions.Potion;

import java.util.List;

public interface PotionsIngredientsService {

    boolean checkIngredientsForOrder(Order order, EnterprisePoint ep);

    boolean checkIngredientsForPotion(PotionNumberForm potionNumberForm, EnterprisePoint ep);

    void createPotionAndStoreInEP(PotionNumberForm potionNumberForm, EnterprisePoint ep);
    boolean requestIngredients(EnterprisePoint ep, String ingredient, int count);

    List<Potion> getAllPotions();

    List<Ingredient> getAllIngredients();
}
