package org.example.server.model.logistics;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.entities.Customer;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer_id;

    @Column(name = "creation_time")
    private Timestamp creation_time;

    @Column(name = "completion_time")
    private Timestamp completion_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status", referencedColumnName = "id", nullable = false)
    private OrderStatus status;
}
