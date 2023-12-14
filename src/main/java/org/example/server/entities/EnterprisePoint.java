package org.example.server.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Enterprise_Point")
public class EnterprisePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "Type", nullable = false)
    private int type;
}
