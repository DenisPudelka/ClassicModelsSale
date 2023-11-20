package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.employee.EmployeeBasicDTO;
import com.example.classicmodlesslaes.dto.employee.EmployeeDetailDTO;
import com.example.classicmodlesslaes.dto.mappers.EmployeeMapper;
import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.service.interfaces.EmployeeService;
import com.example.classicmodlesslaes.service.interfaces.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classicmodelssales")
public class EmployeeController {


    private EmployeeService employeeService;
    private OfficeService officeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, OfficeService officeService) {
        this.employeeService = employeeService;
        this.officeService = officeService;
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

    // needs work
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDetailDTO> updateEmployee(@PathVariable int id, @RequestBody EmployeeDetailDTO employeeDetailDTO){
        return null;
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
