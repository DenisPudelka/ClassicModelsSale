package com.example.classicmodlesslaes.dto.office;

import com.example.classicmodlesslaes.dto.employee.EmployeeBasicDTO;
import com.example.classicmodlesslaes.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OfficeDetailDTO {
    private String officeCode;
    private String city;
    private String phone;
    private String addressLineOne;
    private String addressLineTwo;
    private String state;
    private String country;
    private String postalCode;
    private String territory;
    private List<EmployeeBasicDTO> employees;
}
