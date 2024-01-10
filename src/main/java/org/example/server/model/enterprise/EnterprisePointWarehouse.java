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
@Table(name = "enterprise_point_warehouse")
public class EnterprisePointWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ep_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
    private Ingredient ingredient;

    private int amountOfIngredient;
}
