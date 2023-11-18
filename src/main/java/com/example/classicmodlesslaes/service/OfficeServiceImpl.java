package com.example.classicmodlesslaes.service;

import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.repository.interfaces.OfficeRepository;
import com.example.classicmodlesslaes.service.exceptions.DataAccessException;
import com.example.classicmodlesslaes.service.exceptions.EntityNotFoundException;
import com.example.classicmodlesslaes.service.interfaces.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository){
        this.officeRepository = officeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Office getOfficeById(String id) {
        Office office = officeRepository.getOfficeById(id);
        if(office == null){
            throw new EntityNotFoundException("Office with ID: " + id + " not found");
        }
        return office;
    }

    @Override
    @Transactional
    public Office saveOffice(Office office) {
        try {
            return officeRepository.saveOffice(office);
        }catch (Exception e){
            throw new DataAccessException("Error saving office.", e);
        }
    }

    @Override
    @Transactional
    public Office updateOffice(Office office) {
        if(!existingOffice(office.getOfficeCode())){
            throw new EntityNotFoundException("Cannot update. Office with ID: " + office.getOfficeCode() + " not found");
        }
        try {
            return officeRepository.updateOffice(office);
        }catch (Exception e){
            throw new DataAccessException("Error updating office.", e);
        }
    }

    @Override
    @Transactional
    public void deleteOffice(String id) {
        if(!existingOffice(id)){
            throw new EntityNotFoundException("Cannot delete. Office with ID: " + id + " not found");
        }
        boolean wasDeleted = officeRepository.deleteOffice(id);
        if(!wasDeleted){
            throw new EntityNotFoundException("Office with ID: " + id + " not found and could not be deleted.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> getAllOffices() {
        try {
            List<Office> offices = officeRepository.getAllOffices();
            if(offices == null || offices.isEmpty()){
                throw new EntityNotFoundException("No offices found");
            }
            return offices;
        }catch (Exception e){
            throw new DataAccessException("Error retrieving all offices.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesByCity(String city) {
        List<Office> offices = officeRepository.findOfficesByCity(city);
        if(offices == null || offices.isEmpty()){
            throw new EntityNotFoundException("No offices found in city: " + city);
        }
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesByCountry(String country) {
        List<Office> offices = officeRepository.findOfficesByCountry(country);
        if(offices == null || offices.isEmpty()){
            throw new EntityNotFoundException("No offices found in country: " + country);
        }
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesByTerritory(String territory) {
        List<Office> offices = officeRepository.findOfficesByTerritory(territory);
        if(offices == null || offices.isEmpty()){
            throw new EntityNotFoundException("No offices found in territory: " + territory);
        }
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findOfficesWithPhonePattern(String pattern) {
        List<Office> offices = officeRepository.findOfficesWithPhonePattern(pattern);
        if(offices == null || offices.isEmpty()){
            throw new EntityNotFoundException("No offices found with pattern: " + pattern);
        }
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> searchOfficesByAddress(String keyword) {
        List<Office> offices = officeRepository.searchOfficesByAddress(keyword);
        if(offices == null || offices.isEmpty()){
            throw new EntityNotFoundException("No offices found with address: " + keyword);
        }
        return offices;
    }

    @Override
    @Transactional(readOnly = true)
    public int countOfficesByCountry(String country) {
        int count = officeRepository.countOfficesByCountry(country);
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllTerritories() {
        List<String> territories = officeRepository.findAllTerritories();
        if(territories == null || territories.isEmpty()){
            throw new EntityNotFoundException("No offices found");
        }
        return territories;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> countOfficesByTerritory() {
        try {
            List<Object[]> counts = officeRepository.countOfficesByTerritory();
            if (counts == null || counts.isEmpty()) {
                throw new EntityNotFoundException("No office counts by territory found.");
            }
            return counts;
        } catch (Exception e) {
            throw new DataAccessException("Error retrieving office counts by territory.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> countEmployeesPerOffice() {
        try {
            List<Object[]> counts = officeRepository.countEmployeesPerOffice();
            if (counts == null || counts.isEmpty()) {
                throw new EntityNotFoundException("No employee counts per office found.");
            }
            return counts;
        } catch (Exception e) {
            throw new DataAccessException("Error retrieving employee counts per office.", e);
        }
    }

    private boolean existingOffice(String id) {
        return officeRepository.getOfficeById(id) != null;
    }
}
