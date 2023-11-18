package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerRepository {
    // Basic CRUD
    Customer getCustomerById(int id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    List<Customer> getAllCustomers();

    // Specific queries
    List<Customer> getCustomersByFirstName(String firstName);
    List<Customer> getCustomersByLastName(String lastName);
    List<Customer> getCustomersByCompanyName(String companyName);
    List<Customer> getCustomersByCityName(String city);
    List<Customer> getCustomersByCountry(String country);
    List<Customer> getCustomersWithCreditLimitBeyond(BigDecimal limit);
    List<Customer> getCustomersWithoutSalesRep();

}
