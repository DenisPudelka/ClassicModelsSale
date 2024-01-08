package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.employee.*;
import com.example.classicmodlesslaes.dto.mappers.CustomerMapper;
import com.example.classicmodlesslaes.dto.mappers.EmployeeMapper;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.service.interfaces.CustomerService;
import com.example.classicmodlesslaes.service.interfaces.EmployeeService;
import com.example.classicmodlesslaes.service.interfaces.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classicmodelssales")
public class EmployeeController {


    private EmployeeService employeeService;
    private OfficeService officeService;
    private CustomerService customerService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, OfficeService officeService, CustomerService customerService) {
        this.employeeService = employeeService;
        this.officeService = officeService;
        this.customerService = customerService;
    }


    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeBasicDTO>> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        List<EmployeeBasicDTO> employeeBasicDTOS = employees.stream()
                .map(EmployeeMapper::toEmployeeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeBasicDTOS);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDetailDTO> getEmployeeId(@PathVariable int id){
        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDetailDTO employeeBasicDTO = EmployeeMapper.toEmployeeDetailDTO(employee);
        return ResponseEntity.ok(employeeBasicDTO);
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDetailDTO> createEmployee(@RequestBody EmployeeBasicDTO employeeBasicDTO){
        Employee supervisor = employeeService.getEmployeeById(employeeBasicDTO.getSupervisor());
        Office office = officeService.getOfficeById(employeeBasicDTO.getOffice());
        Employee employee = EmployeeMapper.toEmployeeEntity(employeeBasicDTO);
        employee.setSupervisor(supervisor);
        employee.setOffice(office);
        Employee createEmployee = employeeService.saveEmployee(employee);
        EmployeeDetailDTO employeeDetailDTO = EmployeeMapper.toEmployeeDetailDTO(createEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeDetailDTO);
    }


    @PutMapping("/employees/{id}/details")
    public ResponseEntity<EmployeeBasicDTO> updatePersonalDetailOfEmployee(@PathVariable int id, @RequestBody EmployeePersonalUpdateDTO employeePersonalUpdateDTO){
        Employee existingEmployee = employeeService.getEmployeeById(id);

        Employee employeeToUpdate = EmployeeMapper.toEmployeeEntityPersonalDetailUpdate(employeePersonalUpdateDTO);
        employeeToUpdate.setEmployeeNumber(existingEmployee.getEmployeeNumber());
        employeeToUpdate.setSupervisor(existingEmployee.getSupervisor());

        Office existingOffice = officeService.getOfficeById(employeePersonalUpdateDTO.getOfficeCode());
        employeeToUpdate.setOffice(existingOffice);

        Employee updateEmployee = employeeService.updateEmployee(employeeToUpdate);

        return ResponseEntity.ok(EmployeeMapper.toEmployeeBasicDTO(updateEmployee));
    }

    @PutMapping("/employees/{id}/customers")
    public ResponseEntity<EmployeeDetailDTO> updateEmployeeCustomers(@PathVariable int id, @RequestBody EmployeeCustomerUpdateDTO customerUpdate){
        Employee existingEmployee = employeeService.getEmployeeById(id);

        List<Customer> validatedCustomer = new ArrayList<>();
        for(Integer customerId : customerUpdate.getCustomerIds()){
            Customer customer = customerService.getCustomerById(customerId);
            validatedCustomer.add(customer);
        }

        validatedCustomer.forEach(customer -> customer.setSalesRep(existingEmployee));

        for(Customer customer : validatedCustomer){
            customerService.updateCustomer(customer);
        }

        List<CustomerBasicDTO> customerBasicDTOS = validatedCustomer.stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList());

        EmployeeDetailDTO updatedEmployeeDTO = EmployeeMapper.toEmployeeDetailDTO(existingEmployee);
        updatedEmployeeDTO.setCustomers(customerBasicDTOS);

        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    @PutMapping("/employees/{id}/subordinates")
    public ResponseEntity<EmployeeDetailDTO> updateEmployeeSubordinates(@PathVariable int id, @RequestBody EmployeeSuborinateUpdateDTO subordinateUpdate){
        Employee existingEmployee = employeeService.getEmployeeById(id);

        List<Employee> validatedSubordinates  = new ArrayList<>();
        for(Integer subordinateId : subordinateUpdate.getSubordinateIds()){
            if (id == subordinateId) {
                // An employee cannot be their own subordinate
                return ResponseEntity.notFound().build();
            }
            Employee employee = employeeService.getEmployeeById(subordinateId);
            validatedSubordinates.add(employee);
        }

        validatedSubordinates.forEach(subordinate -> subordinate.setSupervisor(existingEmployee));
        validatedSubordinates.forEach(subordinate -> employeeService.saveEmployee(subordinate));

        List<EmployeeBasicDTO> employeeBasicDTOS = validatedSubordinates.stream()
                .map(EmployeeMapper::toEmployeeBasicDTO)
                .collect(Collectors.toList());

        EmployeeDetailDTO updatedEmployeeDTO = EmployeeMapper.toEmployeeDetailDTO(existingEmployee);
        updatedEmployeeDTO.setSubordinates(employeeBasicDTOS);

        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployeeId(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/employees/job-title")
    public ResponseEntity<List<EmployeeDetailDTO>> findEmployeesByJobTitle(@RequestParam String jobTitle){
        List<Employee> employees = employeeService.findEmployeesByJobTitle(jobTitle);
        List<EmployeeDetailDTO> employeeDetailDTOS = employees.stream()
                .map(EmployeeMapper::toEmployeeDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeDetailDTOS);
    }

    @GetMapping("/employees/without-supervisor")
    public ResponseEntity<List<EmployeeBasicDTO>> findEmployeesWithoutSupervisors(){
        List<Employee> employees = employeeService.findEmployeesWithoutSupervisors();
        List<EmployeeBasicDTO> employeeBasicDTOS = employees.stream()
                .map(EmployeeMapper::toEmployeeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeBasicDTOS);
    }

    @GetMapping("/employees/by-office-code")
    public ResponseEntity<List<EmployeeBasicDTO>> findByOfficeCode(@RequestParam String officeCode){
        List<Employee> employees = employeeService.findByOfficeCode(officeCode);
        List<EmployeeBasicDTO> employeeBasicDTOS = employees.stream()
                .map(EmployeeMapper::toEmployeeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeBasicDTOS);
    }

    @GetMapping("/employees/supervisor-of-employee")
    public ResponseEntity<EmployeeDetailDTO> findSupervisorOfEmployee(@RequestParam int employeeId){
        Employee employee = employeeService.findSupervisorOfEmployee(employeeId);
        EmployeeDetailDTO employeeDetailDTO = EmployeeMapper.toEmployeeDetailDTO(employee);
        return ResponseEntity.ok(employeeDetailDTO);
    }

    @GetMapping("/employees/without-emial")
    public ResponseEntity<List<EmployeeBasicDTO>> findEmployeesWithNoEmail(){
        List<Employee> employees = employeeService.findEmployeesWithNoEmail();
        List<EmployeeBasicDTO> employeeBasicDTOS = employees.stream()
                .map(EmployeeMapper::toEmployeeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeBasicDTOS);
    }
}
