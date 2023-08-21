package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.repository.interfaces.PaymentRepository;
import com.example.classicmodlesslaes.service.interfaces.PaymentService;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public  PaymentServiceImpl (PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment getPaymentById(String id) {
        return paymentRepository.getPaymentById(id);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.updatePayment(payment);
    }

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.savePayment(payment);
    }

    @Override
    public void deletePayment(String id) {
        paymentRepository.deletePayment(id);
    }

    @Override
    public List<Payment> getPaymentsByCustomerNumber(int customerNumber) {
        return paymentRepository.getPaymentsByCustomerNumber(customerNumber);
    }

    @Override
    public List<Payment> getPaymentsAboveAmount(BigDecimal amount) {
        return paymentRepository.getPaymentsAboveAmount(amount);
    }

    @Override
    public BigDecimal getTotalPaymentsByCustomer(int customerNumber) {
        return paymentRepository.getTotalPaymentsByCustomer(customerNumber);
    }

    @Override
    public List<Payment> getPaymentsByDate(LocalDate paymentDate) {
        return paymentRepository.getPaymentsByDate(paymentDate);
    }

    @Override
    public List<Customer> getCustomersWithPaymentsAbove(BigDecimal amount) {
        return paymentRepository.getCustomersWithPaymentsAbove(amount);
    }

    @Override
    public Payment getLastPaymentByCustomer(int customerNumber) {
        return paymentRepository.getLastPaymentByCustomer(customerNumber);
    }

    @Override
    public BigDecimal getTotalPaymentsInMonthYear(int month, int year) {
        return paymentRepository.getTotalPaymentsInMonthYear(month,year);
    }

    @Override
    public List<Customer> getCustomersWithoutPayments() {
        return paymentRepository.getCustomersWithoutPayments();
    }
}
