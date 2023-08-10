package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
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

    @Column(name = "paymentdate", nullable = true)
    private LocalDate paymentDate;

    @Column(name = "amount", nullable = true)
    private BigDecimal amount;

    public Payment(String checkNumber, Customer customer, LocalDate paymentDate, BigDecimal amount) {
        this.checkNumber = checkNumber;
        this.customer = customer;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }
}
