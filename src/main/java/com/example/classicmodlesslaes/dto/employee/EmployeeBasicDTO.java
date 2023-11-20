package com.example.classicmodlesslaes.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.Inet4Address;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeBasicDTO {
    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String jobTitle;
    private Integer supervisor;
    private String office;
}
