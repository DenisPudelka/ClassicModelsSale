package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.repository.interfaces.CustomerRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
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
        if(customer == null){
            throw new EntityNotFoundException("Customer with ID: " + id + " not found");
        }
        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        try {
            customerRepository.createCustomer(customer);
        } catch (Exception e){
            throw new DataAccessException("Error creating customer.", e);
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            return customerRepository.getAllCustomers();
        }catch (Exception e){
            throw new DataAccessException("Error fetching all cusotmers", e);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if(!customerExists(customer.getCustomerNumber())){
            throw new EntityNotFoundException("Cannot updated. Customer with ID " + customer.getCustomerName() + " not found");
        }
        try {
            return customerRepository.updateCustomer(customer);
        }catch (Exception e){
            throw new DataAccessException("Error updating customer", e);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        if(!customerExists(id)){
            throw new EntityNotFoundException("Cannot delete. Customer with ID " + id + " not found");
        }
        customerRepository.deleteCustomer(id);
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        List<Customer> customers = customerRepository.getCustomersByName(name);
        if(customers == null || customers.isEmpty()){
            throw new EntityNotFoundException("No customers found with the name: " + name);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersByCountry(String country) {
        List<Customer> customers = customerRepository.getCustomersByCountry(country);
        if(customers == null || customers.isEmpty()){
            throw new EntityNotFoundException("No customers found in the country: " + country);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersWithCreditLimitBeyond(BigDecimal limit) {
        List<Customer> customers = customerRepository.getCustomersWithCreditLimitBeyond(limit);
        if(customers == null || customers.isEmpty()){
            throw new EntityNotFoundException("No customers found with credit limit: " + limit);
        }
        return customers;
    }

    @Override
    public List<Customer> getCustomersWithoutSalesRep() {
        List<Customer> customers = customerRepository.getCustomersWithoutSalesRep();
        if(customers == null || customers.isEmpty()){
            throw new EntityNotFoundException("No customers found with a sales representative.");
        }
        return customers;
    }

    private boolean customerExists(int id){
        return customerRepository.getCustomerById(id) != null;
    }
}
