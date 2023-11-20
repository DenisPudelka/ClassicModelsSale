package com.example.classicmodlesslaes.dto.employee;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.office.OfficeBasicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailDTO {
    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String jobTitle;
    private EmployeeBasicDTO supervisor;
    private OfficeBasicDTO office;
    private List<EmployeeBasicDTO> subordinates;
    private List<CustomerBasicDTO> customers;
}
