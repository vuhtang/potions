package org.example.server.model.potions;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.logistics.Order;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders_potions")
public class OrdersToPotions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "potion_id", referencedColumnName = "id", nullable = false)
    private Potion potion;

    @Column(name = "amount_of_potions")
    private Integer amountOfPotions;
}
