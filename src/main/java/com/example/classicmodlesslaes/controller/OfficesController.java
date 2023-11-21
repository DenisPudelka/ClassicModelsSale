package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.OfficeMapper;
import com.example.classicmodlesslaes.dto.office.OfficeBasicDTO;
import com.example.classicmodlesslaes.dto.office.OfficeDetailDTO;
import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.service.interfaces.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class OfficesController {

    @Autowired
    private OfficeService officeService;

    @GetMapping("/offices")
    public ResponseEntity<List<OfficeBasicDTO>> getAllOffices(){
        List<Office> offices = officeService.getAllOffices();
        List<OfficeBasicDTO> officeBasicDTOS = offices.stream()
                .map(OfficeMapper::toOfficeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(officeBasicDTOS);
    }

    @GetMapping("/offices/{id}")
    public ResponseEntity<OfficeDetailDTO> getOfficeById(@PathVariable String id){
        Office office = officeService.getOfficeById(id);
        OfficeDetailDTO officeDetailDTO = OfficeMapper.toOfficeDetailDTO(office);
        return ResponseEntity.ok(officeDetailDTO);
    }

    @PostMapping("/offices")
    public ResponseEntity<OfficeDetailDTO> createOffice(@RequestBody OfficeBasicDTO officeDTO){
        Office office = OfficeMapper.toOfficeEntity(officeDTO);
        Office createOffice = officeService.saveOffice(office);
        OfficeDetailDTO officeDetailDTO = OfficeMapper.toOfficeDetailDTO(createOffice);
        return ResponseEntity.status(HttpStatus.CREATED).body(officeDetailDTO);
    }

    // needs work
    @PutMapping("/offices/{name}")
    public ResponseEntity<OfficeDetailDTO> updateOffice(@PathVariable String id, @RequestBody Office office){
        return null;
    }

    @DeleteMapping("/offices/{name}")
    public ResponseEntity<Void> deleteOffice(@PathVariable String name){
        officeService.deleteOffice(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/offices/by-city")
    public ResponseEntity<List<OfficeBasicDTO>> findOfficesByCity(@RequestParam String city){
        List<Office> offices = officeService.findOfficesByCity(city);
        List<OfficeBasicDTO> officeBasicDTOS = offices.stream()
                .map(OfficeMapper::toOfficeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(officeBasicDTOS);
    }

    @GetMapping("/offices/by-country")
    public ResponseEntity<List<OfficeBasicDTO>> findOfficesByCountry(@RequestParam String country){
        List<Office> offices = officeService.findOfficesByCountry(country);
        List<OfficeBasicDTO> officeBasicDTOS = offices.stream()
                .map(OfficeMapper::toOfficeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(officeBasicDTOS);
    }

    @GetMapping("/offices/by-territory")
    public ResponseEntity<List<OfficeBasicDTO>> findOfficesByTerritory(@RequestParam String territory){
        List<Office> offices = officeService.findOfficesByTerritory(territory);
        List<OfficeBasicDTO> officeBasicDTOS = offices.stream()
                .map(OfficeMapper::toOfficeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(officeBasicDTOS);
    }

    @GetMapping("/offices/by-phone")
    public ResponseEntity<List<OfficeBasicDTO>> findOfficesWithPhonePattern(@RequestParam String phone){
        List<Office> offices = officeService.findOfficesWithPhonePattern(phone);
        List<OfficeBasicDTO> officeBasicDTOS = offices.stream()
                .map(OfficeMapper::toOfficeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(officeBasicDTOS);
    }

    @GetMapping("/offices/by-address")
    public ResponseEntity<List<OfficeBasicDTO>> findOfficesByAddress(@RequestParam String address){
        List<Office> offices = officeService.searchOfficesByAddress(address);
        List<OfficeBasicDTO> officeBasicDTOS = offices.stream()
                .map(OfficeMapper::toOfficeBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(officeBasicDTOS);
    }

    @GetMapping("/offices/count-by-country")
    public ResponseEntity<Integer> countOfficesByCountry(@RequestParam String country) {
        int count = officeService.countOfficesByCountry(country);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/offices/all-territories")
    public ResponseEntity<List<String>> findAllTerritories() {
        List<String> territories = officeService.findAllTerritories();
        return ResponseEntity.ok(territories);
    }

    @GetMapping("/offices/count-by-territory")
    public ResponseEntity<List<Object[]>> countOfficesByTerritory() {
        List<Object[]> counts = officeService.countOfficesByTerritory();
        return ResponseEntity.ok(counts);
    }

    @GetMapping("/offices/count-employees-per-office")
    public ResponseEntity<List<Object[]>> countEmployeesPerOffice() {
        List<Object[]> counts = officeService.countEmployeesPerOffice();
        return ResponseEntity.ok(counts);
    }
}
