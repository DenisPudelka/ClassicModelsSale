package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.repository.interfaces.OfficeRepository;
import com.example.classicmodlesslaes.service.interfaces.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository){
        this.officeRepository = officeRepository;
    }

    @Override
    public Office getOfficeById(String id) {
        return officeRepository.getOfficeById(id);
    }

    @Override
    public Office saveOffice(Office office) {
        return officeRepository.saveOffice(office);
    }

    @Override
    public Office updateOffice(Office office) {
        return officeRepository.updateOffice(office);
    }

    @Override
    public void deleteOffice(String id) {
        officeRepository.deleteOffice(id);
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepository.getAllOffices();
    }

    @Override
    public List<Office> findOfficesByCity(String city) {
        return officeRepository.findOfficesByCity(city);
    }

    @Override
    public List<Office> findOfficesByCountry(String country) {
        return officeRepository.findOfficesByCountry(country);
    }

    @Override
    public List<Office> findOfficesByTerritory(String territory) {
        return officeRepository.findOfficesByTerritory(territory);
    }

    @Override
    public List<Office> findOfficesWithPhonePattern(String pattern) {
        return officeRepository.findOfficesWithPhonePattern(pattern);
    }

    @Override
    public List<Office> searchOfficesByAddress(String keyword) {
        return officeRepository.searchOfficesByAddress(keyword);
    }

    @Override
    public int countOfficesByCountry(String country) {
        return officeRepository.countOfficesByCountry(country);
    }

    @Override
    public List<String> findAllTerritories() {
        return officeRepository.findAllTerritories();
    }

    @Override
    public List<Object[]> countOfficesByTerritory() {
        return officeRepository.countOfficesByTerritory();
    }

    @Override
    public List<Object[]> countEmployeesPerOffice() {
        return officeRepository.countEmployeesPerOffice();
    }
}
