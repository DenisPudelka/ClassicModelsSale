package com.example.classicmodlesslaes.dto.employee;

import com.example.classicmodlesslaes.dto.customer.CustomerBasicDTO;
import com.example.classicmodlesslaes.dto.office.OfficeBasicDTO;
import com.example.classicmodlesslaes.model.Customer;
import com.example.classicmodlesslaes.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    public EmployeeDetailDTO toEmployeeDetailDTO(Employee employee){
        EmployeeDetailDTO dto = new EmployeeDetailDTO();
        dto.setEmployeeNumber(employee.getEmployeeNumber());
        dto.setLastName(employee.getLastName());
        dto.setFirstName(employee.getFirstName());
        dto.setExtension(employee.getExtension());
        dto.setEmail(employee.getEmail());
        dto.setJobTitle(employee.getJobTitle());

        if(employee.getSupervisor() != null){
            EmployeeBasicDTO supervisorDTO = new EmployeeBasicDTO();

            supervisorDTO.setEmployeeNumber(employee.getSupervisor().getEmployeeNumber());
            supervisorDTO.setLastName(employee.getSupervisor().getLastName());
            supervisorDTO.setFirstName(employee.getSupervisor().getFirstName());
            supervisorDTO.setExtension(employee.getSupervisor().getExtension());
            supervisorDTO.setEmail(employee.getSupervisor().getEmail());
            supervisorDTO.setJobTitle(employee.getSupervisor().getJobTitle());

            dto.setSupervisor(supervisorDTO);
        }

        if(employee.getOffice() != null){
            OfficeBasicDTO officeBasicDTO = new OfficeBasicDTO();

            officeBasicDTO.setOfficeCode(employee.getOffice().getOfficeCode());
            officeBasicDTO.setCity(employee.getOffice().getCity());
            officeBasicDTO.setPhone(employee.getOffice().getPhone());
            officeBasicDTO.setAddressLineOne(employee.getOffice().getAddressLineOne());
            officeBasicDTO.setAddressLineTwo(employee.getOffice().getAddressLineTwo());
            officeBasicDTO.setState(employee.getOffice().getState());
            officeBasicDTO.setCountry(employee.getOffice().getCountry());
            officeBasicDTO.setPostalCode(employee.getOffice().getPostalCode());
            officeBasicDTO.setTerritory(employee.getOffice().getTerritory());

            dto.setOffice(officeBasicDTO);
        }

        List<EmployeeBasicDTO> subordinatesList = new ArrayList<>();
        if(employee.getSubordinates() != null && !employee.getSubordinates().isEmpty()){
            for(Employee emp : employee.getSubordinates()){
                EmployeeBasicDTO employeeBasicDTO = new EmployeeBasicDTO();

                employeeBasicDTO.setEmployeeNumber(emp.getEmployeeNumber());
                employeeBasicDTO.setLastName(emp.getLastName());
                employeeBasicDTO.setFirstName(emp.getFirstName());
                employeeBasicDTO.setExtension(emp.getExtension());
                employeeBasicDTO.setEmail(emp.getEmail());
                employeeBasicDTO.setJobTitle(emp.getJobTitle());

                subordinates.add(employeeBasicDTO);
            }
        }
        dto.setSubordinates(subordinatesList);

        List<CustomerBasicDTO> customersList = new ArrayList<>();
        if(employee.getCustomers() != null && !employee.getCustomers().isEmpty()){
            for(Customer customer : employee.getCustomers()){
                CustomerBasicDTO customerBasicDTO = new CustomerBasicDTO();

                customerBasicDTO.setCustomerName(customer.getCustomerName());
                customerBasicDTO.setCustomerNumber(customer.getCustomerNumber());
                customerBasicDTO.setContactLastName(customer.getContactLastName());
                customerBasicDTO.setContactFirstName(customer.getContactFirstName());
                customerBasicDTO.setPhone(customer.getPhone());
                customerBasicDTO.setAddressLineOne(customer.getAddressLineOne());
                customerBasicDTO.setAddressLineTwo(customer.getAddressLineTwo());
                customerBasicDTO.setCity(customer.getCity());
                customerBasicDTO.setState(customer.getState());
                customerBasicDTO.setPostalCode(customer.getPostalCode());
                customerBasicDTO.setCountry(customer.getCountry());
                customerBasicDTO.setCreditLimit(customer.getCreditLimit());

                customers.add(customerBasicDTO);
            }
        }
        dto.setCustomers(customersList);

        return dto;
    }
}
