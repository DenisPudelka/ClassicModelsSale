package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.customer.CustomerDetailDTO;
import com.example.classicmodlesslaes.dto.mappers.CustomerMapper;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/customers/search")
    public String searchCustomersByNameAndCountry(@RequestParam(name = "firstName", required = false) String firstName,
                                                  @RequestParam(name = "lastName", required = false) String lastName,
                                                  @RequestParam(name = "country", required = false) String country,
                                                  @RequestParam(name = "city", required = false) String city,
                                                  @RequestParam(name = "companyName", required = false) String companyName
                                                  ,Model model){
        List<Customer> filteredCustomers;

        if(firstName != null && !firstName.isEmpty()){
            filteredCustomers = customerService.getCustomersByFistName(firstName);
        }else if (lastName != null && !lastName.isEmpty()) {
            filteredCustomers = customerService.getCustomersByLastName(lastName);
        } else if (country != null && !country.isEmpty()) {
            filteredCustomers = customerService.getCustomersByCountry(country);
        }else if (companyName != null && !companyName.isEmpty()) {
            filteredCustomers = customerService.getCustomersByCompanyName(companyName);
        }else if (city != null && !city.isEmpty()) {
            filteredCustomers = customerService.getCustomersByCity(city);
        }else {
            filteredCustomers = customerService.getAllCustomers();
        }

        List<CustomerBasicDTO> customerBasicDTOS = filteredCustomers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());

        model.addAttribute("customers", customerBasicDTOS);
        return "customers";
    }

    @GetMapping("/customer/update/{id}")
    public String getUpdateForm(@PathVariable int id, Model model){
        Customer customer = customerService.getCustomerById(id);
        if(customer == null){
            return "error";
        }
        CustomerDetailDTO customerBasicDTO = new CustomerMapper().toCustomerDetailDTO(customer);
        model.addAttribute("customer", customerBasicDTO);
        return "customer-update";
    }

    @PostMapping("/customers/update")
    public String updateCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes){
        customerService.updateCustomer(customer);
        redirectAttributes.addFlashAttribute("message", "Customer updated successfully!");
        return "redirect:/customer/" + customer.getCustomerNumber();

    }
}
