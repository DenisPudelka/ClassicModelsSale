package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.customer.CustomerDetailDTO;
import com.example.classicmodlesslaes.model.Customer;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerBasicDTO toCustomerBasicDTO(Customer customer){
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

    public static CustomerDetailDTO toCustomerDetailDTO(Customer customer){
        if (customer == null) {
            return null;
        }

        CustomerDetailDTO dto = new CustomerDetailDTO();

        CustomerBasicDTO basicDTO = toCustomerBasicDTO(customer);

        dto.setCustomerNumber(basicDTO.getCustomerNumber());
        dto.setCustomerName(basicDTO.getCustomerName());
        dto.setContactLastName(basicDTO.getContactLastName());
        dto.setContactFirstName(basicDTO.getContactFirstName());
        dto.setPhone(basicDTO.getPhone());
        dto.setAddressLineOne(basicDTO.getAddressLineOne());
        dto.setAddressLineTwo(basicDTO.getAddressLineTwo());
        dto.setCity(basicDTO.getCity());
        dto.setState(basicDTO.getState());
        dto.setPostalCode(basicDTO.getPostalCode());
        dto.setCountry(basicDTO.getCountry());
        dto.setCreditLimit(basicDTO.getCreditLimit());

        dto.setSalesRep(EmployeeMapper.toEmployeeBasicDTO(customer.getSalesRep()));

        dto.setPayments(customer.getPayments().stream()
                .map(PaymentMapper::toPaymentBasicDTO)
                .collect(Collectors.toList()));

        dto.setOrders(customer.getOrders().stream()
                .map(OrderMapper::toOrderBasicDTO)
                .collect(Collectors.toList()));

        return dto;
    }
}
