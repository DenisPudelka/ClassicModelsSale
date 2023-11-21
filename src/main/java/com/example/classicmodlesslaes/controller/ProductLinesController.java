package com.example.classicmodlesslaes.controller;

import com.example.classicmodlesslaes.dto.mappers.ProductLineMapper;
import com.example.classicmodlesslaes.dto.productline.ProductLineBasicDTO;
import com.example.classicmodlesslaes.dto.productline.ProductLineDetailDTO;
import com.example.classicmodlesslaes.model.ProductLine;
import com.example.classicmodlesslaes.service.interfaces.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<ProductLineDetailDTO> createNewProductLine(@RequestBody ProductLineBasicDTO productLineDTO){
        ProductLine productLine = ProductLineMapper.toProductLineEntity(productLineDTO);
        ProductLine createProductLine = productLineService.saveProductLine(productLine);
        ProductLineDetailDTO productLineDetailDTO = ProductLineMapper.toProductLineDetailDTO(createProductLine);
        return ResponseEntity.status(HttpStatus.CREATED).body(productLineDetailDTO);
    }

    // needs work
    @PutMapping("/productLines/{name}")
    public ResponseEntity<ProductLineBasicDTO> updateProductLine(@PathVariable String name, @RequestBody ProductLine productLine){
        return null;
    }

    // needs work
    @PutMapping("/update-image/{productLineId}")
    public ResponseEntity<Void> updateImageForProductLine(@PathVariable String productLineId, @RequestBody byte[] newImage) {
        return null;
    }

    // needs work
    @PutMapping("/update-description")
    public ResponseEntity<Void> updateProductDescriptionsForProductLine(@RequestBody ProductLine productLine, @RequestParam String newDescription) {
        return null;
    }

    @DeleteMapping("/productLines/{name}")
    public ResponseEntity<Void> deleteProductLine(@PathVariable String name){
        productLineService.deleteProductLine(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/productLines/by-description")
    public ResponseEntity<List<ProductLineDetailDTO>> getProductLinesByDescription(@RequestParam(required = true) String description) {
        List<ProductLine> productLines = productLineService.getProductLinesByDescription(description);
        List<ProductLineDetailDTO> productLineDetailDTOS = productLines.stream()
                .map(ProductLineMapper::toProductLineDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productLineDetailDTOS);
    }

    @GetMapping("/productLines/with-images")
    public ResponseEntity<List<ProductLineDetailDTO>> getProductLinesWithImages() {
        List<ProductLine> productLines = productLineService.getProductLinesWithImages();
        List<ProductLineDetailDTO> productLineDetailDTOS = productLines.stream()
                .map(ProductLineMapper::toProductLineDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productLineDetailDTOS);
    }

    @GetMapping("/productLines/without-images")
    public ResponseEntity<List<ProductLineDetailDTO>> getProductLineWithoutImages() {
        List<ProductLine> productLines = productLineService.getProductLineWithoutImages();
        List<ProductLineDetailDTO> productLineDetailDTOS = productLines.stream()
                .map(ProductLineMapper::toProductLineDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productLineDetailDTOS);
    }

    @GetMapping("/productLines/search")
    public ResponseEntity<List<ProductLineDetailDTO>> searchProductLinesByPartialName(@RequestParam(required = true) String partialName) {
        List<ProductLine> productLines = productLineService.searchProductLinesByPartialName(partialName);
        List<ProductLineDetailDTO> productLineDetailDTOS = productLines.stream()
                .map(ProductLineMapper::toProductLineDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productLineDetailDTOS);
    }

    @GetMapping("/productLines/product-counts")
    public ResponseEntity<Map<String, Long>> countProductsForAllProductLine() {
        Map<String, Long> productCounts = productLineService.countProductsForAllProductLine();
        return ResponseEntity.ok(productCounts);
    }

    @GetMapping("/productLines/below-stock")
    public ResponseEntity<List<ProductLineBasicDTO>> findProductLinesWithProductsBelowStock(@RequestParam(required = false, defaultValue = "5000") int threshold) {
        List<ProductLine> productLines = productLineService.findProductLinesWithProductsBelowStock(threshold);
        List<ProductLineBasicDTO> productLineBasicDTOS = productLines.stream()
                .map(ProductLineMapper::toProductLineBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productLineBasicDTOS);
    }

    @GetMapping("/productLines/by-vendor")
    public ResponseEntity<List<ProductLineBasicDTO>> findProductLinesByVendor(@RequestParam(required = true) String vendorName) {
        List<ProductLine> productLines = productLineService.findProductLinesByVendor(vendorName);
        List<ProductLineBasicDTO> productLineBasicDTOS = productLines.stream()
                .map(ProductLineMapper::toProductLineBasicDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productLineBasicDTOS);
    }
}
