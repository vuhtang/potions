package org.example.server.model.enterprise;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type", referencedColumnName = "id", nullable = false)
    private EnterprisePointType type;
}
