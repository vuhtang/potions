package org.example.server.model;
import lombok.AllArgsConstructor;
import org.example.server.model.potions.Potion;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Potion potion;
    private int count;
}
