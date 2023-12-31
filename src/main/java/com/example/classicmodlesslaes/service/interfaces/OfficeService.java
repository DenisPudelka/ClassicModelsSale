package com.example.classicmodlesslaes.service.interfaces;

import com.example.classicmodlesslaes.model.Office;

import java.util.List;

public interface OfficeService {
    // Basic CRUD
    Office getOfficeById(String id);
    Office saveOffice(Office office);
    Office updateOffice(Office office);
    void deleteOffice(String id);
    List<Office> getAllOffices();

    // Specific
    List<Office> findOfficesByCity(String city);
    List<Office> findOfficesByCountry(String country);
    List<Office> findOfficesByTerritory(String territory);
    List<Office> findOfficesWithPhonePattern(String pattern);
    List<Office> searchOfficesByAddress(String keyword);
    int countOfficesByCountry(String country);
    List<String> findAllTerritories();
    List<Object[]> countOfficesByTerritory();

    // Extensions Beyond The Current Table
    List<Object[]> countEmployeesPerOffice();

}
