package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.customer.CustomerDetailDTO;
import com.example.classicmodlesslaes.dto.mappers.CustomerMapper;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/classicmodelssales")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String getAllCustomers(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        model.addAttribute("customers", customerBasicDTOS);
        return "customers";
    }

    @GetMapping("/customer/{id}")
    public String getCustomer(@PathVariable int id, Model model){
        Customer customer = customerService.getCustomerById(id);
        if(customer == null){
            return "error";
        }
        CustomerDetailDTO customerBasicDTO = new CustomerMapper().toCustomerDetailDTO(customer);
        model.addAttribute("customerDetail", customer);
        return "customer-detail";
    }


}
