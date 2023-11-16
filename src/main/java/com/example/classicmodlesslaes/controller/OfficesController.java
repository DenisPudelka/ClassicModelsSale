package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.OfficeMapper;
import com.example.classicmodlesslaes.dto.office.OfficeBasicDTO;
import com.example.classicmodlesslaes.dto.office.OfficeDetailDTO;
import com.example.classicmodlesslaes.model.Office;
import com.example.classicmodlesslaes.service.interfaces.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<OfficeBasicDTO> createOffice(@RequestBody Office office){
        return null;
    }

    @PutMapping("/offices/{name}")
    public ResponseEntity<OfficeDetailDTO> updateOffice(@PathVariable String id, @RequestBody Office office){
        return null;
    }
}
