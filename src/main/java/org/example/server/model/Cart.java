package org.example.server.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void deleteItem(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                items.remove(existingItem);
                return;
            }
        }
    }

    public void incCount(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                existingItem.setCount(existingItem.getCount() + 1);
                return;
            }
        }
    }

    public void decCount(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                existingItem.setCount(existingItem.getCount() - 1);
                return;
            }
        }
    }

    public void addItem(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getPotion().getId().equals(item.getPotion().getId())) {
                existingItem.setCount(existingItem.getCount() + 1);
                return;
            }
        }

        items.add(item);
    }

    public void removeItem(Long productId) {
        // Удаляем товар из корзины по его ID
        items.removeIf(item -> item.getPotion().getId().equals(productId));
    }
}
