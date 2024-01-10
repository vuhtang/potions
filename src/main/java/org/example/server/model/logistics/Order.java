package org.example.server.model.logistics;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.model.entities.Customer;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer_id;

    @Column(name = "Creation_time")
    private String creation_time;

    @Column(name = "Completion_time")
    private String completion_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_status", referencedColumnName = "id", nullable = false)
    private OrderStatus status;
}
