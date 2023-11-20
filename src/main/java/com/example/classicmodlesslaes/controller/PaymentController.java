package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.customer.CustomerDetailDTO;
import com.example.classicmodlesslaes.dto.mappers.CustomerMapper;
import com.example.classicmodlesslaes.dto.mappers.PaymentMapper;
import com.example.classicmodlesslaes.dto.payment.PaymentBasicDTO;
import com.example.classicmodlesslaes.dto.payment.PaymentDetailDTO;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import com.example.classicmodlesslaes.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class PaymentController {

    private PaymentService paymentService;
    private CustomerService customerService;

    @Autowired
    public PaymentController(PaymentService paymentService, CustomerService customerService) {
        this.paymentService = paymentService;
        this.customerService = customerService;
    }

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
    public ResponseEntity<PaymentDetailDTO> createPayment(@RequestBody PaymentBasicDTO paymentDTO){
        Customer customer = customerService.getCustomerById(paymentDTO.getCustomer());
        Payment payment = PaymentMapper.toPaymentEntity(paymentDTO);
        payment.setCustomer(customer);
        Payment createPayment = paymentService.savePayment(payment);
        PaymentDetailDTO paymentDetailDTO = PaymentMapper.toPaymentDetailDTO(createPayment);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentDetailDTO);
    }

    // needs work
    @PutMapping("/payments/{checkNum}")
    public ResponseEntity<PaymentDetailDTO> updatePayment(@PathVariable String checkNum, @RequestBody Payment payment){
        return null;
    }

    @DeleteMapping("/payments/{checkNum}")
    public ResponseEntity<Void> deletePayment(@PathVariable String checkNum){
        paymentService.deletePayment(checkNum);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/payments/{customerNumber}/payments")
    public ResponseEntity<List<PaymentDetailDTO>> getPaymentsByCustomerNumber(@PathVariable int customerNumber) {
        List<Payment> payments = paymentService.getPaymentsByCustomerNumber(customerNumber);
        List<PaymentDetailDTO> paymentDetailDTOS = payments.stream()
                .map(PaymentMapper::toPaymentDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentDetailDTOS);
    }

    @GetMapping("/payments/{customerNumber}/payments/last")
    public ResponseEntity<PaymentDetailDTO> getLastPaymentByCustomer(@PathVariable int customerNumber) {
        Payment payment = paymentService.getLastPaymentByCustomer(customerNumber);
        PaymentDetailDTO paymentDetailDTO = PaymentMapper.toPaymentDetailDTO(payment);
        return ResponseEntity.ok(paymentDetailDTO);
    }

    @GetMapping("/payments/above-amount")
    public ResponseEntity<List<PaymentBasicDTO>> getPaymentsAboveAmount(@RequestParam(required = true) BigDecimal amount) {
        List<Payment> payments = paymentService.getPaymentsAboveAmount(amount);
        List<PaymentBasicDTO> paymentBasicDTOS = payments.stream()
                .map(PaymentMapper::toPaymentBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentBasicDTOS);
    }

    @GetMapping("/payments/by-date")
    public ResponseEntity<List<PaymentBasicDTO>> getPaymentsByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate paymentDate) {
        List<Payment> payments = paymentService.getPaymentsByDate(paymentDate);
        List<PaymentBasicDTO> paymentBasicDTOS = payments.stream()
                .map(PaymentMapper::toPaymentBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentBasicDTOS);
    }

    @GetMapping("/payments/total-in-month-year")
    public ResponseEntity<BigDecimal> getTotalPaymentsInMonthYear(@RequestParam int month, @RequestParam int year) {
        BigDecimal amount = paymentService.getTotalPaymentsInMonthYear(month, year);
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/payments/customer-without-payments")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersWithoutPayments() {
        List<Customer> customers = paymentService.getCustomersWithoutPayments();
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/payments/customer-with-payments-above")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersWithPaymentsAbove(@RequestParam BigDecimal amount) {
        List<Customer> customers = paymentService.getCustomersWithPaymentsAbove(amount);
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }
}
