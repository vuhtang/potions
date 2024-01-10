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
@Table(name = "enterprise_deliveries")
public class EnterpriseDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ep_from_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePointFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ep_to_id", referencedColumnName = "id", nullable = false)
    private EnterprisePoint enterprisePointTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id", referencedColumnName = "id", nullable = false)
    private Worker courier;

    @Column(name = "creation_time")
    private Timestamp creationTime;

    @Column(name = "completion_time")
    private Timestamp completionTime;
}
