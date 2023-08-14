package com.example.classicmodlesslaes;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Payment;
import com.example.classicmodlesslaes.repository.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
public class ClassicmodlesslaesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassicmodlesslaesApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ProductLineRepository productLineRepository,
                                               ProductRepository productRepository,
                                               OfficeRepository officeRepository,
                                               EmployeeRepository employeeRepository,
                                               PaymentRepository paymentRepository,
                                               OrderRepository orderRepository,
                                               CustomerRepository customerRepository){
        return runner -> {
            //testProductLine(productLineRepository); -- toto robi secko
            //testProduct(productRepository); -- toto robisecko
            //testOffice(officeRepository); -- toto robi secko
            //testOrder(orderRepository, customerRepository); -- toto robi secko
            //testEmployee(employeeRepository, officeRepository);
            testPayment(paymentRepository, customerRepository);
            //testCustomer(customerRepository); -- toto robi secko
        };
    }

    private void testPayment(PaymentRepository paymentRepository, CustomerRepository customerRepository) {
        // get by id
//        String id = "HQ336336";
//        Payment payment = paymentRepository.getPaymentById(id);
//        System.out.println(payment);
//        System.out.println(payment.getCustomer());

        // create payment
//        String id = "HQ000000";
//        Payment payment = new Payment(customerRepository.getCustomerById(103), id, LocalDate.now(), new BigDecimal(500));
//        paymentRepository.savePayment(payment);

        // update
//        String id = "HQ000000";
//        Payment payment = paymentRepository.getPaymentById(id);
//        payment.setAmount(new BigDecimal(999999));
//        paymentRepository.updatePayment(payment);

        // delete
//        paymentRepository.deletePayment("HQ000000");

        // get payments by by customer number
//        List<Payment> payments = paymentRepository.getPaymentsByCustomerNumber(103);
//        for(Payment payment : payments){
//            System.out.println(payment);
//        }

        // get total
//        BigDecimal amount = paymentRepository.getTotalPaymentsByCustomer(103);
//        System.out.println(amount);


    }


}
