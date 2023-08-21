package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.repository.interfaces.CustomerRepository;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer getCustomerById(int id) {
        Customer customer = customerRepository.getCustomerById(id);
        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customerRepository.createCustomer(customer);
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.getCustomersByName(name);
    }

    @Override
    public List<Customer> getCustomersByCountry(String country) {
        return customerRepository.getCustomersByCountry(country);
    }

    @Override
    public List<Customer> getCustomersWithCreditLimitBeyond(BigDecimal limit) {
        return customerRepository.getCustomersWithCreditLimitBeyond(limit);
    }

    @Override
    public List<Customer> getCustomersWithoutSalesRep() {
        return customerRepository.getCustomersWithoutSalesRep();
    }

}
