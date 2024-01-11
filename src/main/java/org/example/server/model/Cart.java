package org.example.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.server.model.potions.Effect;
import org.example.server.model.potions.Potion;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Setter
@Getter
@Component
@ApplicationScope
public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public void deleteItem(CartItem item) {
        if (item == null) return;
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                items.remove(existingItem);
                return;
            }
        }
    }

    public void incCount(CartItem item) {
        if (item == null) return;
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                existingItem.setCount(existingItem.getCount() + 1);
                return;
            }
        }
    }

    public void decCount(CartItem item) {
        if (item == null) return;
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                existingItem.setCount(existingItem.getCount() - 1);
                return;
            }
        }
    }

    public void addItem(Potion potion) {
        if (potion == null) return;

        Optional<CartItem> o = items.stream().filter(cartItem -> cartItem.getPotion().getId().equals(potion.getId())).findFirst();
        o.ifPresent(items::remove);

        items.add(new CartItem(potion, 0));
    }
    public void addItem(CartItem item) {
        if (item == null) return;
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                existingItem.setCount(item.getCount());
                return;
            }
        }

        if (!items.contains(item))
            items.add(item);
    }

    public void removeItem(Integer productId) {
        if (productId == null) return;
        // Удаляем товар из корзины по его ID
        items.removeIf(item -> item.getPotion().getId().equals(productId));
    }
}
