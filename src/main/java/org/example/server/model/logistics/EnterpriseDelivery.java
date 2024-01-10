package org.example.server.model.logistics;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.entities.Worker;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Enterprise_Deliveries")
public class EnterpriseDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EP_from_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePointFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EP_to_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePointTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Courier_id", referencedColumnName = "id", nullable = false)
    private Worker courier;

    @Column(name = "Creation_time")
    private Timestamp creationTime;

    @Column(name = "Completion_time")
    private Timestamp completionTime;
}
