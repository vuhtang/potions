package org.example.server.entities;


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

    @Column(name = "Customer_id", nullable = false)
    private int customer_id;

    @Column(name = "Creation_time")
    private String creation_time;

    @Column(name = "Completion_time")
    private String completion_time;

    @Column(name = "Order_status", nullable = false)
    private int status;
}

//todo подумать над FK
//ForExample:
//@ManyToOne
//@JoinColumn(name = "user_id", referencedColumnName = "id")
//private User user;
