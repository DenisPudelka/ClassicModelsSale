package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Payment;

public interface PaymentRepository {
    Payment getPaymentById(String id);
}
