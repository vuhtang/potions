package org.example.server.model.enterprise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.potions.Ingredient;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Enterprise_Point_Warehouse")
public class EnterprisePointWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EP_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ingredient_id", referencedColumnName = "id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "Amount_of_Ingredients")
    private int amountOfIngredient;
}
