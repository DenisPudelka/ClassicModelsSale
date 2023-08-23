package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.mappers.CustomerMapper;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/classicmodelssales")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerBasicDTO>> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerBasicDTO> customerBasicDTOS = new ArrayList<>();
        for(Customer c : customers){
            customerBasicDTOS.add(new CustomerMapper().toCustomerBasicDTO(c));
        }
        return ResponseEntity.ok(customerBasicDTOS);
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerBasicDTO> getCustomer(@PathVariable int id){
        Customer customer = customerService.getCustomerById(id);
        if(customer == null){
            return ResponseEntity.notFound().build();
        }
        CustomerBasicDTO customerBasicDTO = new CustomerMapper().toCustomerBasicDTO(customer);
        return ResponseEntity.ok(customerBasicDTO);
    }
}
