package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.repository.interfaces.EmployeeRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        try {
            return employeeRepository.getAllEmployees();
        }catch (Exception e){
            throw new DataAccessException("Error fetching all employees: ", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        if(employee == null){
            throw new EntityNotFoundException("Employee with ID: " + id + " not found.");
        }
        return employee;
    }

    @Override
    @Transactional
    public Employee saveEmployee(Employee employee) {
        try {
            return employeeRepository.saveEmployee(employee);
        }catch (Exception e){
            throw new DataAccessException("Error saving employee.", e);
        }
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        if(!existingEmployee(employee.getEmployeeNumber())){
            throw new EntityNotFoundException("Cannot update. Employee with ID: " + employee.getEmployeeNumber() + " not found.");
        }
        try {
            return employeeRepository.updateEmployee(employee);
        }catch (Exception e){
            throw new DataAccessException("Error updating employee.", e);
        }
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        if(!existingEmployee(id)){
            throw new EntityNotFoundException("Cannot delete. Employee with ID: " + id + " not found");
        }
        boolean wasDeleted = employeeRepository.deleteEmployee(id);
        if(!wasDeleted){
            throw new EntityNotFoundException("Employee with ID: " + id + " not found and could not be deleted.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findEmployeesByJobTitle(String jobTitle) {
        List<Employee> employees = employeeRepository.findEmployeesByJobTitle(jobTitle);
        if(employees == null || employees.isEmpty()){
            throw new EntityNotFoundException("No employees found with the job title: " + jobTitle);
        }
        return employees;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findEmployeesWithoutSupervisors() {
        List<Employee> employees = employeeRepository.findEmployeesWithoutSupervisors();
        if(employees == null || employees.isEmpty()){
            throw new EntityNotFoundException("No employees from with supervisor");
        }
        return employees;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByOfficeCode(String officeCode) {
        List<Employee> employees = employeeRepository.findByOfficeCode(officeCode);
        if(employees == null || employees.isEmpty()){
            throw new EntityNotFoundException("No employee found for office code: " + officeCode);
        }
        return employees;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findSupervisorOfEmployee(int employeeId) {
        Employee supervisor = employeeRepository.findSupervisorOfEmployee(employeeId);
        if(supervisor == null){
            throw new EntityNotFoundException("No supervisor found for employee: " + employeeId);
        }
        return supervisor;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findEmployeesWithNoEmail() {
        List<Employee> employees = employeeRepository.findEmployeesWithNoEmail();
        if(employees == null || employees.isEmpty()){
            throw new EntityNotFoundException("No employees found without an email address");
        }
        return employees;
    }

    private boolean existingEmployee(int id){
        return employeeRepository.getEmployeeById(id) != null;
    }
}
