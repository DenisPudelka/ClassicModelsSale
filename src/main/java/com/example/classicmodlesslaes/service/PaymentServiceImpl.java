package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.repository.interfaces.PaymentRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.PaymentService;
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
    @Transactional(readOnly = true)
    public List<Payment> getAllPayments() {
        try {
            return paymentRepository.getAllPayments();
        }catch (Exception e){
            throw new DataAccessException("Error fetching all payments", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getPaymentById(String id) {
        Payment payment = paymentRepository.getPaymentById(id);
        if(payment == null){
            throw new EntityNotFoundException("Payment with ID: " + id + " not found.");
        }
        return payment;
    }

    @Override
    @Transactional
    public Payment updatePayment(Payment payment) {
        if(!paymentExists(payment.getCheckNumber())) {
            throw new EntityNotFoundException("Cannot update. Payment not found.");
        }
        try {
            return paymentRepository.updatePayment(payment);
        } catch (Exception e) {
            throw new DataAccessException("Error updating payment.", e);
        }
    }

    @Override
    @Transactional
    public Payment savePayment(Payment payment) {
        try {
            return paymentRepository.savePayment(payment);
        } catch (Exception e) {
            throw new DataAccessException("Error saving payment.", e);
        }
    }

    @Override
    @Transactional
    public void deletePayment(String id) {
        if(!paymentExists(id)) {
            throw new EntityNotFoundException("Cannot delete. Payment with ID: " + id + " not found.");
        }
        boolean wasDeleted = paymentRepository.deletePayment(id);
        if(!wasDeleted){
            throw new EntityNotFoundException("Payment with ID: " + id + " not found and could not be deleted.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getPaymentsByCustomerNumber(Integer customerNumber) {
        List<Payment> payments = paymentRepository.getPaymentsByCustomerNumber(customerNumber);
        if(payments == null || payments.isEmpty()) {
            throw new EntityNotFoundException("No payments found for customer number: " + customerNumber);
        }
        return payments;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getPaymentsAboveAmount(BigDecimal amount) {
        List<Payment> payments = paymentRepository.getPaymentsAboveAmount(amount);
        if(payments == null || payments.isEmpty()){
            throw new EntityNotFoundException("No payments with amount above: " + amount);
        }
        return payments;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPaymentsByCustomer(int customerNumber) {
        BigDecimal totalPayments =  paymentRepository.getTotalPaymentsByCustomer(customerNumber);
        if(totalPayments == null){
            throw new EntityNotFoundException("No payments by customer number: " + customerNumber);
        }
        return totalPayments;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Payment> getPaymentsByDate(LocalDate paymentDate) {
        List<Payment> payments = paymentRepository.getPaymentsByDate(paymentDate);
        if(payments == null || payments.isEmpty()){
            throw new EntityNotFoundException("No payments by date: " + paymentDate);
        }
        return payments;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getCustomersWithPaymentsAbove(BigDecimal amount) {
        List<Customer> customers = paymentRepository.getCustomersWithPaymentsAbove(amount);
        if(customers == null || customers.isEmpty()){
            throw new EntityNotFoundException("No payments found with amount: " + amount);
        }
        return customers;
    }

    @Override
    @Transactional(readOnly = true)
    public Payment getLastPaymentByCustomer(int customerNumber) {
        Payment payment = paymentRepository.getLastPaymentByCustomer(customerNumber);
        if(payment == null){
            throw new EntityNotFoundException("No payments found customer number: " + customerNumber);
        }
        return payment;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalPaymentsInMonthYear(int month, int year) {
        BigDecimal bigDecimal = paymentRepository.getTotalPaymentsInMonthYear(month,year);
        if(bigDecimal == null){
            throw new EntityNotFoundException("No payments for month: " + month + " year: " + year);
        }
        return bigDecimal;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getCustomersWithoutPayments() {
        List<Customer> customers = paymentRepository.getCustomersWithoutPayments();
        if(customers == null || customers.isEmpty()){
            throw new EntityNotFoundException("No customer with payments");
        }
        return customers;
    }

    private boolean paymentExists(String id) {
        return paymentRepository.getPaymentById(id) != null;
    }
}
