package com.example.classicmodlesslaes.dto.mappers;

import com.example.classicmodlesslaes.dto.office.OfficeBasicDTO;
import com.example.classicmodlesslaes.dto.office.OfficeDetailDTO;
import com.example.classicmodlesslaes.model.Office;

import java.util.stream.Collectors;

public class OfficeMapper {

    public static OfficeBasicDTO toOfficeBasicDTO(Office office) {
        OfficeBasicDTO dto = new OfficeBasicDTO();

        dto.setOfficeCode(office.getOfficeCode());
        dto.setCity(office.getCity());
        dto.setPhone(office.getPhone());
        dto.setAddressLineOne(office.getAddressLineOne());
        dto.setAddressLineTwo(office.getAddressLineTwo());
        dto.setState(office.getState());
        dto.setCountry(office.getCountry());
        dto.setPostalCode(office.getPostalCode());
        dto.setTerritory(office.getTerritory());

        return dto;
    }

    public static OfficeDetailDTO toOfficeDetailDTO(Office office) {
        OfficeDetailDTO dto = new OfficeDetailDTO();

        dto.setOfficeCode(office.getOfficeCode());
        dto.setCity(office.getCity());
        dto.setPhone(office.getPhone());
        dto.setAddressLineOne(office.getAddressLineOne());
        dto.setAddressLineTwo(office.getAddressLineTwo());
        dto.setState(office.getState());
        dto.setCountry(office.getCountry());
        dto.setPostalCode(office.getPostalCode());
        dto.setTerritory(office.getTerritory());

        dto.setEmployees(office.getEmployees().stream()
                .map(EmployeeMapper::toEmployeeBasicDTO)
                .collect(Collectors.toList()));

        return dto;
    }

    public static Office toOfficeEntity(OfficeBasicDTO officeDTO){
        if(officeDTO == null){
            return null;
        }

        Office office = new Office();
        office.setOfficeCode(officeDTO.getOfficeCode());
        office.setCity(officeDTO.getCity());
        office.setPhone(officeDTO.getPhone());
        office.setAddressLineOne(officeDTO.getAddressLineOne());
        office.setAddressLineTwo(officeDTO.getAddressLineTwo());
        office.setState(officeDTO.getState());
        office.setCountry(officeDTO.getCountry());
        office.setPostalCode(officeDTO.getPostalCode());
        office.setTerritory(officeDTO.getTerritory());

        return office;
    }
}
