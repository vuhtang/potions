package org.example.server.model.potions;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.entities.LivingThing;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "Living_Thing_id", referencedColumnName = "id")
    private LivingThing livingThing;
}
