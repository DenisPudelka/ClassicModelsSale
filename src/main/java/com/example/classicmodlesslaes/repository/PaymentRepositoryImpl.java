package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.repository.interfaces.PaymentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public Payment getPaymentById(String id) {
        Payment payment = entityManager.find(Payment.class, id);
        return payment;
    }

    @Override
    @Transactional
    public Payment updatePayment(Payment payment) {
        return entityManager.merge(payment);
    }

    @Override
    @Transactional
    public Payment savePayment(Payment payment) {
        entityManager.persist(payment);
        return payment;
    }

    @Override
    @Transactional
    public void deletePayment(String id) {
        Payment payment = entityManager.find(Payment.class, id);
        entityManager.remove(payment);
    }

    @Override
    public List<Payment> getPaymentsByCustomerNumber(int customerNumber) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.customer.customerNumber = :customerNumber",
                Payment.class);
        query.setParameter("customerNumber", customerNumber);
        return query.getResultList();
    }

    @Override
    public List<Payment> getPaymentsAboveAmount(BigDecimal amount) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.amount > :amount",
                Payment.class);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    @Override
    public BigDecimal getTotalPaymentsByCustomer(int customerNumber) {
        TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT SUM(p.amount) FROM Payment p WHERE p.customer.customerNumber = :customerNumber",
                BigDecimal.class);
        query.setParameter("customerNumber", customerNumber);

        BigDecimal total = query.getSingleResult();

        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public List<Payment> getPaymentsByDate(LocalDate paymentDate) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.paymentDate = :paymentDate",
                Payment.class);
        query.setParameter("paymentDate", paymentDate);

        List<Payment> payments = query.getResultList();

        return payments;
    }

    @Override
    public List<Customer> getCustomersWithPaymentsAbove(BigDecimal amount) {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT DISTINCT p.customer FROM Payment p WHERE p.amount > :amount",
                Customer.class);
        query.setParameter("amount", amount);
        return query.getResultList();
    }

    @Override
    public Payment getLastPaymentByCustomer(int customerNumber) {
        TypedQuery<Payment> query = entityManager.createQuery(
                "SELECT p FROM Payment p WHERE p.customer.customerNumber = :customerNumber ORDER BY p.paymentDate DESC",
                Payment.class);
        query.setParameter("customerNumber", customerNumber);
        query.setMaxResults(1); // Get only the most recent one
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public BigDecimal getTotalPaymentsInMonthYear(int month, int year) {
        TypedQuery<BigDecimal> query = entityManager.createQuery(
                "SELECT SUM(p.amount) FROM Payment p WHERE FUNCTION('MONTH', p.paymentDate) = :month AND FUNCTION('YEAR', p.paymentDate) = :year",
                BigDecimal.class);
        query.setParameter("month", month);
        query.setParameter("year", year);
        return query.getSingleResult();
    }

    @Override
    public List<Customer> getCustomersWithoutPayments() {
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE NOT EXISTS (SELECT 1 FROM Payment p WHERE p.customer.customerNumber = c.customerNumber)",
                Customer.class);
        return query.getResultList();
    }
}
