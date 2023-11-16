package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.payment.PaymentBasicDTO;
import com.example.classicmodlesslaes.dto.payment.PaymentDetailDTO;
import com.example.classicmodlesslaes.model.Payment;

public class PaymentMapper {

    public static PaymentBasicDTO toPaymentBasicDTO(Payment payment){
        PaymentBasicDTO dto = new PaymentBasicDTO();
        dto.setCheckNumber(payment.getCheckNumber());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());
        return dto;
    }

    public static PaymentDetailDTO toPaymentDetailDTO(Payment payment){
        PaymentDetailDTO dto = new PaymentDetailDTO();
        dto.setCheckNumber(payment.getCheckNumber());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());

        //CustomerBasicDTO customerDTO = CustomerMapper.toCustomerBasicDTO(payment.getCustomer());
        dto.setCustomer(CustomerMapper.toCustomerBasicDTO(payment.getCustomer()));

        return dto;
    }
}
