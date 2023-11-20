package com.example.classicmodlesslaes.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

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
    private Integer customer;
}

