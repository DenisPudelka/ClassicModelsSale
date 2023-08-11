package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.repository.interfaces.PaymentRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
