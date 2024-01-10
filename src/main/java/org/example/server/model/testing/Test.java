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
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "potion_id", referencedColumnName = "id", nullable = false)
    private Potion potion;

    @Column(name = "test_date")
    private Date testDate;

    @ManyToOne
    @JoinColumn(name = "test_status", referencedColumnName = "id", nullable = false)
    private TestStatus testStatus;

    @ManyToOne
    @JoinColumn(name = "worker_id", referencedColumnName = "id", nullable = false)
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "living_thing_id", referencedColumnName = "id")
    private LivingThing livingThing;

    @Column(name = "duration")
    private int duration;
}
