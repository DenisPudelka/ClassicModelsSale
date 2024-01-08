package com.example.classicmodlesslaes.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCustomerUpdateDTO {
    private List<Integer> customerIds;
}
