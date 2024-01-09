package com.example.classicmodlesslaes.dto.office;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficeEmployeeUpdateDTO {
    private List<Integer> employeeIds;
}
