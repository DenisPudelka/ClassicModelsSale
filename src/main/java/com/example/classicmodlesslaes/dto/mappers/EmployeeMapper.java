package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.employee.EmployeeBasicDTO;
import com.example.classicmodlesslaes.dto.employee.EmployeeDetailDTO;
import com.example.classicmodlesslaes.dto.employee.EmployeePersonalUpdateDTO;
import com.example.classicmodlesslaes.model.Employee;

import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeBasicDTO toEmployeeBasicDTO(Employee employee) {
        EmployeeBasicDTO dto = new EmployeeBasicDTO();

        dto.setEmployeeNumber(employee.getEmployeeNumber());
        dto.setLastName(employee.getLastName());
        dto.setFirstName(employee.getFirstName());
        dto.setExtension(employee.getExtension());
        dto.setEmail(employee.getEmail());
        dto.setJobTitle(employee.getJobTitle());
        dto.setOffice(employee.getOffice().getOfficeCode());
        //dto.setSupervisor(employee.getSupervisor().getEmployeeNumber());

        return dto;
    }

    public static EmployeeDetailDTO toEmployeeDetailDTO(Employee employee) {
        EmployeeDetailDTO dto = new EmployeeDetailDTO();

        dto.setEmployeeNumber(employee.getEmployeeNumber());
        dto.setLastName(employee.getLastName());
        dto.setFirstName(employee.getFirstName());
        dto.setExtension(employee.getExtension());
        dto.setEmail(employee.getEmail());
        dto.setJobTitle(employee.getJobTitle());

        dto.setOffice(OfficeMapper.toOfficeBasicDTO(employee.getOffice()));

        dto.setSupervisor(toEmployeeBasicDTO(employee.getSupervisor()));

        dto.setSubordinates(employee.getSubordinates().stream()
                .map(EmployeeMapper::toEmployeeBasicDTO)
                .collect(Collectors.toList()));

        dto.setCustomers(employee.getCustomers().stream()
                .map(CustomerMapper::toCustomerBasicDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    public static Employee toEmployeeEntity(EmployeeBasicDTO employeeDTO){
        if(employeeDTO == null){
            return null;
        }

        Employee employee = new Employee();
        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setExtension(employeeDTO.getExtension());
        employee.setEmail(employeeDTO.getEmail());
        employee.setJobTitle(employeeDTO.getJobTitle());

        return employee;
    }

    public static Employee toEmployeeEntityPersonalDetailUpdate(EmployeePersonalUpdateDTO employeePersonalUpdateDTO){
        if(employeePersonalUpdateDTO == null){
            return null;
        }

        Employee employee = new Employee();
        employee.setLastName(employeePersonalUpdateDTO.getLastName());
        employee.setFirstName(employeePersonalUpdateDTO.getFirstName());
        employee.setExtension(employeePersonalUpdateDTO.getExtension());
        employee.setEmail(employeePersonalUpdateDTO.getEmail());
        employee.setJobTitle(employeePersonalUpdateDTO.getJobTitle());

        return employee;
    }
}
