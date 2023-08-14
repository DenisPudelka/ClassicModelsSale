package com.example.classicmodlesslaes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    @Column(name = "ordernumber")
    private int orderNumber;

    @Column(name = "productcode")
    private String productCode;

    public OrderDetailId(int orderNumber, String productCode) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
    }
}
