package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.repository.interfaces.EmployeeRepository;
import com.example.classicmodlesslaes.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.saveEmployee(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeRepository.deleteEmployee(id);
    }

    @Override
    public List<Employee> findEmployeesByJobTitle(String jobTitle) {
        return employeeRepository.findEmployeesByJobTitle(jobTitle);
    }

    @Override
    public List<Employee> findEmployeesWithoutSupervisors() {
        return employeeRepository.findEmployeesWithoutSupervisors();
    }

    @Override
    public List<Employee> findByOfficeCode(String officeCode) {
        return employeeRepository.findByOfficeCode(officeCode);
    }

    @Override
    public Employee findSupervisorOfEmployee(int employeeId) {
        return employeeRepository.findSupervisorOfEmployee(employeeId);
    }

    @Override
    public List<Employee> findEmployeesWithNoEmail() {
        return employeeRepository.findEmployeesWithNoEmail();
    }

}
