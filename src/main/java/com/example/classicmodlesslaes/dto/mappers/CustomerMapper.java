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

    public static Customer toCustomerEntity(CustomerBasicDTO dto){
        if(dto == null){
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomerName(dto.getCustomerName());
        customer.setContactLastName(dto.getContactLastName());
        customer.setContactFirstName(dto.getContactFirstName());
        customer.setPhone(dto.getPhone());
        customer.setAddressLineOne(dto.getAddressLineOne());
        customer.setAddressLineTwo(dto.getAddressLineTwo());
        customer.setCity(dto.getCity());
        customer.setState(dto.getState());
        customer.setPostalCode(dto.getPostalCode());
        customer.setCountry(dto.getCountry());
        customer.setCreditLimit(dto.getCreditLimit());
        // If your DTO includes an employee (sales rep) identifier, set it here
        //customer.setSalesRep(EmployeeMapper.toEmployeeEntity(dto.getSalesRep()));
        //customer.setSalesRep(dto.getSalesRep());
        return customer;
    }

    public static void updateCustomerEntityWithDetails(Customer customer, CustomerDetailDTO customerDetailDTO) {
        customer.setCustomerName(customerDetailDTO.getCustomerName());
        customer.setContactLastName(customerDetailDTO.getContactLastName());
        customer.setContactFirstName(customerDetailDTO.getContactFirstName());
        customer.setPhone(customerDetailDTO.getPhone());
        customer.setAddressLineOne(customerDetailDTO.getAddressLineOne());
        customer.setAddressLineTwo(customerDetailDTO.getAddressLineTwo());
        customer.setCity(customerDetailDTO.getCity());
        customer.setState(customerDetailDTO.getState());
        customer.setPostalCode(customerDetailDTO.getPostalCode());
        customer.setCountry(customerDetailDTO.getCountry());
        customer.setCreditLimit(customerDetailDTO.getCreditLimit());
        //customer.setSalesRep(customerDetailDTO.getSalesRep());
//        customer.setPayments(customerDetailDTO.getPayments());
//        customer.setOrders(customerDetailDTO.getOrders());
    }
}
