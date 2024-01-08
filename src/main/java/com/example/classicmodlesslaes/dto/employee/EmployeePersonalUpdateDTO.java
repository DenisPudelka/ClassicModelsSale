package com.example.classicmodlesslaes.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePersonalUpdateDTO {
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String officeCode;
    private String jobTitle;
}
