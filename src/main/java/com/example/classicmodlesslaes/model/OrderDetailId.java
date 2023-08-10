package com.example.classicmodlesslaes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    @Column(name = "ordernumber")
    private int orderNumber;

    @Column(name = "productcode")
    private String productCode;
}
