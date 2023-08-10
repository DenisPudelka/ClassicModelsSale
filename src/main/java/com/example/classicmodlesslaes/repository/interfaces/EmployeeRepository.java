package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Employee;

public interface EmployeeRepository {
    Employee getEmployeeById(int id);
    void saveEmployee(Employee employee);
    void updateEmployee(Employee employee);
    int deleteEmployee(int id);
}
