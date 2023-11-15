package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.ProductLineMapper;
import com.example.classicmodlesslaes.dto.productline.ProductLineBasicDTO;
import com.example.classicmodlesslaes.dto.productline.ProductLineDetailDTO;
import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.service.interfaces.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classicmodelssales")
public class ProductLinesController {

    @Autowired
    private ProductLineService productLineService;

    @GetMapping("/productLines")
    public ResponseEntity<List<ProductLineBasicDTO>> getAllProductLines(){
        List<ProductLine> productLines = productLineService.getAllProductLines();
        List<ProductLineBasicDTO> productLineBasicDTOS = productLines.stream().
                map(ProductLineMapper::toProductLineBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productLineBasicDTOS);
    }

    @GetMapping("/productLines/{name}")
    public ResponseEntity<ProductLineDetailDTO> getProductLineByName(@PathVariable String name){
        ProductLine productLine = productLineService.getProductLineById(name);
        ProductLineDetailDTO productLineDetailDTO = ProductLineMapper.toProductLineDetailDTO(productLine);
        return ResponseEntity.ok(productLineDetailDTO);
    }

    @PostMapping("/productLines")
    public ResponseEntity<ProductLineBasicDTO> createNewProductLine(@RequestBody ProductLine productLine){
        return null;
    }

    @PutMapping("/productLines/{name}")
    public ResponseEntity<ProductLineBasicDTO> updateProductLine(@PathVariable String name, @RequestBody ProductLine productLine){
        return null;
    }

    @DeleteMapping("/productLines/{name}")
    public ResponseEntity<ProductLine> deleteProductLine(@PathVariable String name){
        return null;
    }
}
