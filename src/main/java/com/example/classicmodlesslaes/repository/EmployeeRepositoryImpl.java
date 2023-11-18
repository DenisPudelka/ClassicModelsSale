package com.example.classicmodlesslaes.repository;

import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Employee;
import com.example.classicmodlesslaes.repository.interfaces.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EntityManager entityManager;

    @Autowired
    public EmployeeRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployees() {
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        if (employee != null){
            employee.setSupervisor(null);
            entityManager.remove(employee);
            return true;
        }
        return false;
    }

    @Override
    public List<Employee> findEmployeesByJobTitle(String jobTitle) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.jobTitle = :jobTitle",
                Employee.class);
        query.setParameter("jobTitle", jobTitle);
        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeesWithoutSupervisors() {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.supervisor IS NULL",
                Employee.class);
        return query.getResultList();
    }

    @Override
    public List<Employee> findByOfficeCode(String officeCode) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.office.officeCode = :officeCode",
                Employee.class);
        query.setParameter("officeCode", officeCode);
        return query.getResultList();
    }

    @Override
    public Employee findSupervisorOfEmployee(int employeeId) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e.supervisor FROM Employee e WHERE e.employeeNumber = :employeeId",
                Employee.class);
        query.setParameter("employeeId", employeeId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Employee> findEmployeesWithNoEmail() {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.email IS NULL OR e.email = ''",
                Employee.class);
        return query.getResultList();
    }
}
