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
@Table(name = "enterprise_point_coldwarehouse")
public class EnterprisePointColdWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ep_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "potion_id", referencedColumnName = "id", nullable = false)
    private Potion potion;

    @Column(name = "amount_of_ingredient")
    private int amountOfPotions;
}
