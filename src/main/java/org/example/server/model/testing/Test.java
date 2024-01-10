package org.example.server.model.testing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.entities.Worker;
import org.example.server.model.entities.LivingThing;
import org.example.server.model.potions.Potion;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Potion_id", referencedColumnName = "id", nullable = false)
    private Potion potion;

    @Column(name = "Test_date")
    private Date testDate;

    @ManyToOne
    @JoinColumn(name = "Test_status", referencedColumnName = "id", nullable = false)
    private TestStatus testStatus;

    @ManyToOne
    @JoinColumn(name = "Worker_id", referencedColumnName = "id", nullable = false)
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "Living_Thing_id", referencedColumnName = "id")
    private LivingThing livingThing;

    @Column(name = "Duration")
    private int duration;
}
