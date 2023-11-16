package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.PaymentMapper;
import com.example.classicmodlesslaes.dto.payment.PaymentBasicDTO;
import com.example.classicmodlesslaes.dto.payment.PaymentDetailDTO;
import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentBasicDTO>> getAllPayments(){
        List<Payment> payments = paymentService.getAllPayments();
        List<PaymentBasicDTO> paymentBasicDTOS = payments.stream()
                .map(PaymentMapper::toPaymentBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentBasicDTOS);
    }

    @GetMapping("/payments/{checkNum}")
    public ResponseEntity<PaymentDetailDTO> getPaymentByCheckNum(@PathVariable String checkNum){
        Payment payment = paymentService.getPaymentById(checkNum);
        PaymentDetailDTO paymentDetailDTO = PaymentMapper.toPaymentDetailDTO(payment);
        return ResponseEntity.ok(paymentDetailDTO);
    }

    @PostMapping("/payments")
    public ResponseEntity<PaymentBasicDTO> createPayment(@RequestBody Payment payment){
        return null;
    }

    @PutMapping("/payments/{checkNum}")
    public ResponseEntity<PaymentDetailDTO> updatePayment(@PathVariable String checkNum, @RequestBody Payment payment){
        return null;
    }

    @DeleteMapping("/payments/{checkNum}")
    public ResponseEntity<PaymentBasicDTO> deletePayment(@PathVariable String checkNum){
        return null;
    }
}
