package com.example.classicmodlesslaes.dto.office;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfficeBasicDTO {
    private String officeCode;
    private String city;
    private String phone;
    private String addressLineOne;
    private String addressLineTwo;
    private String state;
    private String country;
    private String postalCode;
    private String territory;
}
