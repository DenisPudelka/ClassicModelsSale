package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.repository.interfaces.PaymentRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    private EntityManager entityManager;

    @Autowired
    public PaymentRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Payment getPaymentById(String id) {
        Payment payment = entityManager.find(Payment.class, id);
        return payment;
    }

    @Override
    public void updatePayment(Payment payment) {

    }

    @Override
    public void savePayment(Payment payment) {

    }

    @Override
    public void deletePayment(String id) {

    }

    @Override
    public List<Payment> getPaymentsByCustomerNumber(int customerNumber) {
        return null;
    }

    @Override
    public List<Payment> getPaymentsAboveAmount(BigDecimal amount) {
        return null;
    }

    @Override
    public BigDecimal getTotalPaymentsByCustomer(int customerNumber) {
        return null;
    }

    @Override
    public List<Payment> getPaymentsByDate(LocalDate paymentDate) {
        return null;
    }

    @Override
    public List<Customer> getCustomersWithPaymentsAbove(BigDecimal amount) {
        return null;
    }

    @Override
    public Payment getLastPaymentByCustomer(int customerNumber) {
        return null;
    }

    @Override
    public BigDecimal getTotalPaymentsInMonthYear(int month, int year) {
        return null;
    }

    @Override
    public List<Customer> getCustomersWithoutPayments() {
        return null;
    }
}
