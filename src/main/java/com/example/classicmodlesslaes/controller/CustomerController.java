package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.customer.CustomerDetailDTO;
import com.example.classicmodlesslaes.dto.mappers.CustomerMapper;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import com.example.classicmodlesslaes.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class CustomerController {

    private CustomerService customerService;
    private EmployeeService employeeService;

    @Autowired
    public CustomerController(CustomerService customerService, EmployeeService employeeService) {
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerBasicDTO>> getAllCustomers(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDetailDTO> getCustomer(@PathVariable int id){
        Customer customer = customerService.getCustomerById(id);
        CustomerDetailDTO customerDetailDTO = CustomerMapper.toCustomerDetailDTO(customer);
        return ResponseEntity.ok(customerDetailDTO);
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDetailDTO> createCustomer(@RequestBody CustomerBasicDTO customerDTO){
        Employee salesRep = employeeService.getEmployeeById(customerDTO.getSalesRep());
        Customer customer = CustomerMapper.toCustomerEntity(customerDTO);
        customer.setSalesRep(salesRep);
        Customer createCustomer = customerService.createCustomer(customer);
        CustomerDetailDTO customerDetailDTO = CustomerMapper.toCustomerDetailDTO(createCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDetailDTO);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDetailDTO> updateCustomer(@PathVariable int id, @RequestBody CustomerBasicDTO customerDTO){
        Customer existingCustomer = customerService.getCustomerById(id);
        if(existingCustomer == null){
            return ResponseEntity.notFound().build();
        }

        Customer customerToUpdate = CustomerMapper.toCustomerEntity(customerDTO);
        customerToUpdate.setCustomerNumber(existingCustomer.getCustomerNumber());

        if(customerDTO.getSalesRep() != null){
            Employee employee = employeeService.getEmployeeById(customerDTO.getSalesRep());
            if(employee == null){
                return ResponseEntity.notFound().build();
            }
            customerToUpdate.setSalesRep(employee);
        }

        Customer updatedCustomer = customerService.updateCustomer(customerToUpdate);

        return ResponseEntity.ok(CustomerMapper.toCustomerDetailDTO(updatedCustomer));
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customers/firstname/{firstName}")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersByFirstName(@PathVariable String firstName){
        List<Customer> customers = customerService.getCustomersByFirstName(firstName);
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/customers/lastname/{lastName}")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersByLastName(@PathVariable String lastName){
        List<Customer> customers = customerService.getCustomersByLastName(lastName);
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/customers/company/{companyName}")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersByCompanyName(@PathVariable String companyName){
        List<Customer> customers = customerService.getCustomersByCompanyName(companyName);
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/customers/city/{cityName}")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersByCity(@PathVariable String cityName){
        List<Customer> customers = customerService.getCustomersByCity(cityName);
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/customers/country/{countryName}")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersByCountry(@PathVariable String countryName){
        List<Customer> customers = customerService.getCustomersByCountry(countryName);
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/customers/credit-limit-beyond/{limit}")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersWithCreditLimitBeyond(@PathVariable BigDecimal limit) {
        List<Customer> customers = customerService.getCustomersWithCreditLimitBeyond(limit);
        List<CustomerBasicDTO> customerBasicDTOS = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOS);
    }

    @GetMapping("/customers/without-sales-rep")
    public ResponseEntity<List<CustomerBasicDTO>> getCustomersWithoutSalesRep() {
        List<Customer> customers = customerService.getCustomersWithoutSalesRep();
        List<CustomerBasicDTO> customerBasicDTOs = customers.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerBasicDTOs);
    }
}
