package com.example.classicmodlesslaes.repository.interfaces;

import com.example.classicmodlesslaes.model.Office;

import java.util.List;

public interface OfficeRepository {
    // Basic CRUD
    Office getOfficeById(String id);
    Office saveOffice(Office office);
    Office updateOffice(Office office);
    boolean deleteOffice(String id);
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
