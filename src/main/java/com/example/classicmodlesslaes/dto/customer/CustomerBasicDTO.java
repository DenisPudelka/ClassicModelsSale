package com.example.classicmodlesslaes.dto.customer;

import com.example.classicmodlesslaes.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBasicDTO {
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

    public CustomerBasicDTO toDTO(Customer customer){
        if (customer == null) {
            return null;
        }

        CustomerBasicDTO dto = new CustomerBasicDTO();
        dto.setCustomerNumber(customer.getCustomerNumber());
        dto.setCustomerName(customer.getCustomerName());
        dto.setContactLastName(customer.getContactLastName());
        dto.setContactFirstName(customer.getContactFirstName());
        dto.setPhone(customer.getPhone());
        dto.setAddressLineOne(customer.getAddressLineOne());
        dto.setAddressLineTwo(customer.getAddressLineTwo());
        dto.setCity(customer.getCity());
        dto.setState(customer.getState());
        dto.setPostalCode(customer.getPostalCode());
        dto.setCountry(customer.getCountry());
        dto.setCreditLimit(customer.getCreditLimit());

        return dto;
    }

    public Customer toEntity(CustomerBasicDTO customerBasicDTO){
        if(customerBasicDTO == null){
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomerNumber(customerBasicDTO.getCustomerNumber());
        customer.setCustomerName(customerBasicDTO.getCustomerName());
        customer.setContactLastName(customerBasicDTO.getContactLastName());
        customer.setContactFirstName(customerBasicDTO.getContactFirstName());
        customer.setPhone(customerBasicDTO.getPhone());
        customer.setAddressLineOne(customerBasicDTO.getAddressLineOne());
        customer.setAddressLineTwo(customerBasicDTO.getAddressLineTwo());
        customer.setCity(customerBasicDTO.getCity());
        customer.setState(customerBasicDTO.getState());
        customer.setPostalCode(customerBasicDTO.getPostalCode());
        customer.setCountry(customerBasicDTO.getCountry());
        customer.setCreditLimit(customerBasicDTO.getCreditLimit());

        return customer;
    }
}
