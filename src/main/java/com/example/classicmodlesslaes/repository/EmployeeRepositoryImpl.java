package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.repository.interfaces.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EntityManager entityManager;

    @Autowired
    public EmployeeRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    @Override
    @Transactional
    public int deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        return employee.getEmployeeNumber();
    }
}
