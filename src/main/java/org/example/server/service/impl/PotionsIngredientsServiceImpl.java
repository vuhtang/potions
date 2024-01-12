package org.example.server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.server.model.PotionNumberForm;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.enterprise.EnterprisePointWarehouse;
import org.example.server.model.logistics.Order;
import org.example.server.model.potions.Ingredient;
import org.example.server.model.potions.OrdersToPotions;
import org.example.server.model.potions.Potion;
import org.example.server.model.potions.PotionToIngr;
import org.example.server.repository.*;
import org.example.server.service.EPService;
import org.example.server.service.PotionsIngredientsService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
@Primary
public class PotionsIngredientsServiceImpl implements PotionsIngredientsService {

    private final PotionsRepository potionsRepository;
    private final IngredientsRepository ingredientsRepository;
    private final PotionToOrderRepository potionToOrderRepository;
    private final PotionToIngrRepository potionToIngrRepository;
    private final EPWarehouseRepository epWarehouseRepository;
    private final EPService epService;

    @Override
    public boolean checkIngredientsForPotion(PotionNumberForm potionNumberForm, EnterprisePoint ep) {
        Integer potionId = potionNumberForm.getPotionId();
        Optional<Potion> potionOptional = potionsRepository.findById(potionId);
        if (potionOptional.isEmpty())
            return false;
        Potion potion = potionOptional.get();
        List<Potion> potions = new ArrayList<>();
        potions.add(potion);
        List<Integer> amounts = new ArrayList<>();
        amounts.add(1);
        Map<Ingredient, Integer> ingredientsAmounts = getIngredientsAndAmounts(potions, amounts);
        List<EnterprisePointWarehouse> epwList = epService.getEPWarehouseItemsByEP(ep);
        for (EnterprisePointWarehouse epw: epwList) {
            Ingredient ingredient = epw.getIngredient();
            if (!ingredientsAmounts.containsKey(ingredient))
                continue;
            Integer i = ingredientsAmounts.get(ingredient);
            i -= epw.getAmountOfIngredient();
            if (i > 0)
                return false;
            ingredientsAmounts.put(epw.getIngredient(), i);
        }
        for (Integer i: ingredientsAmounts.values()) {
            if (i > 0)
                return false;
        }
        return true;
    }

    @Override
    public void createPotionAndStoreInEP(PotionNumberForm potionNumberForm, EnterprisePoint ep) {
        Optional<Potion> potionOptional = potionsRepository.findById(potionNumberForm.getPotionId());
        if (potionOptional.isEmpty())
            return;
        Potion potion = potionOptional.get();
        epService.addPotionToColdWarehouse(potion, ep);
        List<PotionToIngr> potionToIngrs = potionToIngrRepository.findAllByPotion(potion);
        decreaseIngredients(potionToIngrs, ep);
    }

    private void decreaseIngredients(List<PotionToIngr> potionToIngrs, EnterprisePoint ep) {
        List<Ingredient> ingredients = potionToIngrs.stream().map(PotionToIngr::getIngredient).toList();
        List<Integer> amounts = potionToIngrs.stream().map(PotionToIngr::getAmountOfIngredient).toList();
        List<EnterprisePointWarehouse> epwList = epWarehouseRepository.findAllByEnterprisePoint_Id(ep.getId());
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient =  ingredients.get(i);
            Integer amount = amounts.get(i);
            Optional<EnterprisePointWarehouse> o = epwList.stream().filter(epw -> ingredient.equals(epw.getIngredient())).findFirst();
            if (o.isEmpty())
                return;
            EnterprisePointWarehouse epw = o.get();
            epw.setAmountOfIngredient(epw.getAmountOfIngredient() - amount);
            epWarehouseRepository.save(epw);
        }
    }

    @Override
    public boolean checkIngredientsForOrder(Order order, EnterprisePoint ep) {
        List<OrdersToPotions> otpList = potionToOrderRepository.findAllByOrder(order);
        List<Potion> potions = otpList.stream().map(OrdersToPotions::getPotion).toList();
        List<Integer> amounts = otpList.stream().map(OrdersToPotions::getAmountOfPotions).toList();
        Map<Ingredient, Integer> ingredientsAmounts = getIngredientsAndAmounts(potions, amounts);
        List<EnterprisePointWarehouse> epwList = epService.getEPWarehouseItemsByEP(ep);
        for (EnterprisePointWarehouse epw: epwList) {
            Integer i = ingredientsAmounts.get(epw.getIngredient());
            if (i == null)
                return false;
            i -= epw.getAmountOfIngredient();
            if (i < 0)
                return false;
            ingredientsAmounts.put(epw.getIngredient(), i);
        }
        for (Integer i: ingredientsAmounts.values()) {
            if (i > 0)
                return false;
        }
        return true;
    }

    @Override
    public boolean requestIngredients(EnterprisePoint ep, String ingredient, int count) {
        return false;
    }

    private Map<Ingredient, Integer> getIngredientsAndAmounts(List<Potion> potions, List<Integer> amountsOfPotions) {
        Map<Ingredient, Integer> ingredientAmount = new HashMap<>();

        for (Potion potion: potions) {
            List<PotionToIngr> potionToIngrs = potionToIngrRepository.findAllByPotion(potion);
            for (PotionToIngr potionToIngr: potionToIngrs) {
                if (ingredientAmount.containsKey(potionToIngr.getIngredient())) {
                    Integer i = ingredientAmount.get(potionToIngr.getIngredient());
                    ingredientAmount.put(potionToIngr.getIngredient(), potionToIngr.getAmountOfIngredient() + i);
                } else {
                    ingredientAmount.put(potionToIngr.getIngredient(), potionToIngr.getAmountOfIngredient());
                }
            }
        }

        return ingredientAmount;
    }

    @Override
    public List<Potion> getAllPotions() {
        return potionsRepository.findAll();
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientsRepository.findAll();
    }
}
