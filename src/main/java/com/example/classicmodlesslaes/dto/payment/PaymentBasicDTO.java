package com.example.classicmodlesslaes.dto.payment;

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
public class PaymentBasicDTO {
    private String checkNumber;
    private LocalDate paymentDate;
    private BigDecimal amount;

    public PaymentBasicDTO toPaymentBasicDTO(Payment payment){
        PaymentBasicDTO dto = new PaymentBasicDTO();
        dto.setCheckNumber(payment.getCheckNumber());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());
        return dto;
    }

}
