package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"customer"})
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "checknumber", nullable = false)
    private String checkNumber;

    @ManyToOne
    @JoinColumn(name = "customernumber", nullable = true)
    private Customer customer;

    @Column(name = "paymentdate", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public Payment(Customer customer, String checkNumber, LocalDate paymentDate, BigDecimal amount) {
        this.checkNumber = checkNumber;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.customer = customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
