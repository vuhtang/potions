package org.example.server.model.enterprise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.potions.Potion;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Enterprise_Point_Coldwarehouse")
public class EnterprisePointColdWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EP_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Potion_id", referencedColumnName = "id", nullable = false)
    private Potion potion;

    @Column(name = "Amount_of_Ingredient")
    private int amountOfPotions;
}
