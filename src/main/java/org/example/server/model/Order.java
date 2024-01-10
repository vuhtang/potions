package org.example.server.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer_id;

    @Column(name = "creation_time")
    private String creation_time;

    @Column(name = "completion_time")
    private String completion_time;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "order_status", referencedColumnName = "id", nullable = false)
    private OrderStatus status;
}

//todo подумать над FK
//ForExample:
//@ManyToOne
//@JoinColumn(name = "user_id", referencedColumnName = "id")
//private User user;
