package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "customernumber", nullable = false)
    private int customerNumber;

    @Column(name = "customername", nullable = false)
    private String customerName;

    @Column(name = "contactlastname", nullable = false)
    private String contactLastName;

    @Column(name = "contactfirstname", nullable = false)
    private String contactFirstName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "addressline1", nullable = false)
    private String addressLineOne;

    @Column(name = "addressline2", nullable = true)
    private String addressLineTwo;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "postalcode", nullable = false)
    private String postalCode;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToOne
    @JoinColumn(name = "salesrepemployeenumber")
    private Employee salesRep;

    @Column(name = "creditlimit", nullable = true)
    private BigDecimal creditLimit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Customer(String customerName, String contactLastName, String contactFirstName, String phone, String addressLineOne, String addressLineTwo, String city, String state, String postalCode, String country, Employee salesRepEmployeeNumber, BigDecimal creditLimit, List<Payment> payments, List<Order> orders) {
        this.customerName = customerName;
        this.contactLastName = contactLastName;
        this.contactFirstName = contactFirstName;
        this.phone = phone;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.salesRep = salesRepEmployeeNumber;
        this.creditLimit = creditLimit;
        this.payments = payments;
        this.orders = orders;
    }

    // Utility Methods

    public void addPayment(Payment payment){
        payments.add(payment);
        payment.setCustomer(this);
    }

    public void removePayment(Payment payment){
        payments.remove(payment);
        payment.setCustomer(null);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setCustomer(null);
    }
}
