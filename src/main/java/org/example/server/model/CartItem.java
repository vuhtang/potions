package org.example.server.model;
import org.example.server.model.potions.Potion;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartItem {
    private Potion potion;
    private int count;
}
