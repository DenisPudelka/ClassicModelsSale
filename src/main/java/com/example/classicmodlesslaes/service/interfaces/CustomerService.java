package com.example.classicmodlesslaes.service.interfaces;

import com.example.classicmodlesslaes.model.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {

    // Basic CRUD
    Customer getCustomerById(int id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(int id);
    List<Customer> getAllCustomers();

    // Specific queries
    List<Customer> getCustomersByName(String name);
    List<Customer> getCustomersByCountry(String country);
    List<Customer> getCustomersWithCreditLimitBeyond(BigDecimal limit);
    List<Customer> getCustomersWithoutSalesRep();
}
