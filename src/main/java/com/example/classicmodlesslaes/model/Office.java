package com.example.classicmodlesslaes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "employees")
@Entity
@Table(name = "offices")
public class Office {

    @Id
    @Column(name = "officecode", nullable = false)
    @EqualsAndHashCode.Include
    private String officeCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "addressline1", nullable = false)
    private String addressLineOne;

    @Column(name = "addressline2", nullable = true)
    private String addressLineTwo;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "postalcode", nullable = false)
    private String postalCode;

    @Column(name = "territory", nullable = false)
    private String territory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "office", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private List<Employee> employees = new ArrayList<>();

    public Office(String officeCode, String city, String phone, String addressLineOne, String addressLineTwo, String state, String country, String postalCode, String territory) {
        this.officeCode = officeCode;
        this.city = city;
        this.phone = phone;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.territory = territory;
    }

    // Utility Methods
    public void addEmployee(Employee employee){
        employees.add(employee);
        employee.setOffice(this);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
        employee.setOffice(null);
    }
}
