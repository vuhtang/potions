package org.example.server.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.entities.Human;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Workers")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Human_id", referencedColumnName = "id", nullable = false)
    private Human human;
}
