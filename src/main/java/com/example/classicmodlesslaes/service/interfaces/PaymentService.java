package com.example.classicmodlesslaes.service.interfaces;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    // CRUD
    List<Payment> getAllPayments();
    Payment getPaymentById(String id);
    Payment updatePayment(Payment payment);
    Payment savePayment(Payment payment);
    void deletePayment(String id);

    // Specific Queries
    List<Payment> getPaymentsByCustomerNumber(int customerNumber);
    List<Payment> getPaymentsAboveAmount(BigDecimal amount);
    BigDecimal getTotalPaymentsByCustomer(int customerNumber);
    List<Payment> getPaymentsByDate(LocalDate paymentDate);

    // Extensions Beyond The Current Table
    List<Customer> getCustomersWithPaymentsAbove(BigDecimal amount);
    Payment getLastPaymentByCustomer(int customerNumber);
    BigDecimal getTotalPaymentsInMonthYear(int month, int year);
    List<Customer> getCustomersWithoutPayments();
}
