package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ordernumber", nullable = false)
    private int orderNumber;

    @Column(name = "orderdate", nullable = false)
    private LocalDate orderDate;

    @Column(name = "requireddate", nullable = false)
    private LocalDate requiredDate;

    @Column(name = "shippeddate", nullable = false)
    private LocalDate shippedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    @Column(name = "comments" ,columnDefinition = "TEXT", nullable = true)
    private String comments;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "customernumber")
    private Customer customer;

    public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, OrderStatus status, String comments, Customer customer) {
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comments = comments;
        this.customer = customer;
    }
}
