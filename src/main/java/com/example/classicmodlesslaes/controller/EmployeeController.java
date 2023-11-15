package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.employee.EmployeeBasicDTO;
import com.example.classicmodlesslaes.dto.employee.EmployeeDetailDTO;
import com.example.classicmodlesslaes.dto.mappers.EmployeeMapper;
import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classicmodelssales")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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
    public ResponseEntity<EmployeeBasicDTO> createEmployee(@RequestBody EmployeeBasicDTO employeeBasicDTO){
        return null;
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDetailDTO> updateEmployee(@PathVariable int id, @RequestBody EmployeeDetailDTO employeeDetailDTO){
        return null;
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<EmployeeBasicDTO> deleteEmployeeId(@PathVariable int id){
        return null;
    }
}
