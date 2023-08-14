package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "orderdetails")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailId id;

    @ManyToOne
    @JoinColumn(name = "ordernumber", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productcode", insertable = false, updatable = false)
    private Product product;

    @Column(name = "quantityordered", nullable = false)
    private int quantityOrdered;

    @Column(name = "priceeach", nullable = false)
    private BigDecimal priceEach;

    @Column(name = "orderlinenumber", nullable = false)
    private short orderLineNumber;

    public OrderDetail(OrderDetailId id, Order order, Product product, int quantityOrdered, BigDecimal priceEach, short orderLineNumber) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderLineNumber = orderLineNumber;
    }
}
