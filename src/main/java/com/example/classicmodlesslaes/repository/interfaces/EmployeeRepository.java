package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    // Basic CRUD
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    int deleteEmployee(int id);

    //Specific query
    List<Employee> findEmployeesByJobTitle(String jobTitle);
    List<Employee> findEmployeesWithoutSupervisors();
    List<Employee> findByOfficeCode(String officeCode);
    Employee findSupervisorOfEmployee(int employeeId);
    List<Employee> findEmployeesWithNoEmail();
}
