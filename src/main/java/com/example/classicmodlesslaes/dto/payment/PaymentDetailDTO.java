package com.example.classicmodlesslaes.dto.payment;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDTO {
    private String checkNumber;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private CustomerBasicDTO customer;  // Assuming there's a CustomerBasicDTO class.

    public PaymentDetailDTO toPaymentDetailDTO(Payment payment){
        PaymentDetailDTO dto = new PaymentDetailDTO();
        dto.setCheckNumber(payment.getCheckNumber());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());

        // Convert Customer to CustomerBasicDTO
        CustomerBasicDTO customerDTO = toCustomerBasicDTO(payment.getCustomer()); // Assuming you have the method toCustomerBasicDTO for Customer.
        dto.setCustomer(customerDTO);

        return dto;
    }

}

