package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"orderDetails","customer"})
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordernumber", nullable = false)
    @EqualsAndHashCode.Include
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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, OrderStatus status, String comments, Customer customer) {
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comments = comments;
        this.customer = customer;
    }

    /*
    public void addOrderDetail(Product product, int quantity, BigDecimal price, short lineNumber) {
        OrderDetail detail = new OrderDetail();
        detail.setOrder(this);
        detail.setProduct(product);
        detail.setQuantityOrdered(quantity);
        detail.setPriceEach(price);
        detail.setOrderLineNumber(lineNumber);
        orderDetails.add(detail);
    }
    */

    public void addOrderDetail(OrderDetail orderD){
        if(orderD != null){
            orderDetails.add(orderD);
            orderD.setOrder(this);
        }
    }

    public void removeOrderDetail(OrderDetail detail) {
        orderDetails.remove(detail);
        detail.setOrder(null);
        detail.setProduct(null);
    }
}
