package com.example.classicmodlesslaes.dto.customer;

import com.example.classicmodlesslaes.dto.employee.EmployeeBasicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailDTO extends CustomerBasicDTO {
    private int customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private BigDecimal creditLimit;
    private EmployeeBasicDTO salesRep;
    private List<PaymentDTO> payments;
    private List<OrderDTO> orders;
}
