package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"supervisor", "subordinates", "customers"})
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeenumber", nullable = false)
    @EqualsAndHashCode.Include
    private int employeeNumber;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "jobtitle", nullable = false)
    private String jobTitle;

    @ManyToOne
    @JoinColumn(name = "reportsto")
    private Employee supervisor;

    @ManyToOne
    @JoinColumn(name = "officecode")
    private Office office;

    @OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "supervisor", cascade = { // FetchType.LAZY
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.REMOVE
    })
    private List<Employee> subordinates = new ArrayList<>();


    public Employee(String lastName, String firstName, String extension, String email, String jobTitle, Office office) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.jobTitle = jobTitle;
        this.office = office;
    }

    // Utility methods

    public void addSubordinate(Employee employee){
        subordinates.add(employee);
        employee.setSupervisor(this);
    }

    public void removeSubordinate(Employee employee){
        subordinates.remove(employee);
        employee.setSupervisor(null);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setSalesRep(this);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        customer.setSalesRep(null);
    }
}
