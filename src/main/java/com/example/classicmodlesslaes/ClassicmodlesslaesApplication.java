package com.example.classicmodlesslaes;

import com.example.classicmodlesslaes.model.*;
import com.example.classicmodlesslaes.repository.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ClassicmodlesslaesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassicmodlesslaesApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ProductLineRepository productLineRepository,
                                               ProductRepository productRepository,
                                               OfficeRepository officeRepository,
                                               EmployeeRepository employeeRepository,
                                               PaymentRepository paymentRepository){
        return runner -> {
            testProductLine(productLineRepository);
            //testProduct(productRepository);
            //testOffice(officeRepository);
            //testEmployee(employeeRepository, officeRepository);
            //testPayment(paymentRepository);
        };
    }

    private void testPayment(PaymentRepository paymentRepository) {
        // get payment
        String id = "HQ336336";
        Payment payment = paymentRepository.getPaymentById(id);
        System.out.println(payment);
    }

    private void testEmployee(EmployeeRepository employeeRepository, OfficeRepository officeRepository) {
        // get employee
//        int id = 1056;
//        Employee employee = employeeRepository.getEmployeeById(id);
//        System.out.println(employee);

        // save employee
//        Employee tmp = employeeRepository.getEmployeeById(1002);
//        Office office = officeRepository.getOfficeById("1");
//        Employee employee = new Employee("AAAA", "AAAA", "AAAA", "AAAA", "AAAA", tmp);
//        //employee.setEmployeeNumber(9999);
//        employee.setOffice(office);
//        System.out.println(employee.getEmployeeNumber());
//        employeeRepository.saveEmployee(employee);

        // update employee
//        Employee employee = employeeRepository.getEmployeeById(1703);
//        employee.setEmail("AAAAAAAAAA");
//        employeeRepository.updateEmployee(employee);

        // delete employee
        // TODO: not deleting (no error or  exception thrown)
        int id = employeeRepository.deleteEmployee(1702);
        System.out.println(id);
    }

    private void testOffice(OfficeRepository officeRepository) {
        // get office
//        String id = "1";
//        Office office = officeRepository.getOfficeById(id);
//        System.out.println(office);

        // save office
//        String id = "00";
//        Office office = new Office(id, "AAAA", "AAAA", "AAAAA", null, null, "SRB", "1232123", "SRB");
//        officeRepository.saveOffice(office);

        // update office
//        String id = "00";
//        Office office = officeRepository.getOfficeById(id);
//        office.setCity("BBBBBB");
//        office.setPhone("BBBBBBB");
//        office.setAddressLineOne("BBBBBBB");
//        officeRepository.updateOffice(office);

        // delete office
        String id = "00";
        officeRepository.deleteOffice(id);
    }

    private void testProduct(ProductRepository productRepository) {
        // find by id
        String id = "S10_1678";
        Product product = productRepository.findProductById(id);
        System.out.println(product.toString());

        // create new product line
//        String id = "S00_0000";
//        Product product = new Product(id, null, null, null, null, 0, null, null);
//        productRepository.saveProduct(product);

        // update product
//        String id = "S00_0000";
//        Product product = productRepository.findProductById(id);
//        product.setProductName("TEST");
//        product.setProductDescription("AAAAAAAAAAAAAAAAA");
//        productRepository.updatedProduct(product);

        // delete product
//        String id = "S00_0000";
//        productRepository.deleteProduct(id);
    }

    private void testProductLine(ProductLineRepository productLineRepository) {
        // find product line by id
//        String id = "Trains";
//        ProductLine productLine = productLineRepository.getProductLineById(id);
//        System.out.println(productLine.toString());

        // create new product line
//        String id = "TEST";
//        String desc = "toto je test ci robi";
//        ProductLine newProductLine = new ProductLine(id, desc, null, null);
//        productLineRepository.saveProductLine(newProductLine);

        // delete product line by id
//        String id = "TEST";
//        productLineRepository.deleteProductLine(id);

        // update existing product line
//        String id = "TEST";
//        ProductLine productLine = productLineRepository.getProductLineById(id);
//        productLine.setTextDescription("AAAAAAAAAAAAAAAAAAA");
//        productLineRepository.updateProductLine(productLine);

        // get all products
//        List<ProductLine> productLines = productLineRepository.getAllProductLines();
//        for(ProductLine productLine : productLines){
//            System.out.println(productLine.getProductLine());
//        }

        // get product line by description
//        String des = "car";
//        List<ProductLine> productLines = productLineRepository.getProductLinesByDescription(des);
//        for(ProductLine productLine : productLines){
//            System.out.println(productLine.getProductLine());
//        }

        // get product line by with image
//        List<ProductLine> productLines = productLineRepository.getProductLinesWithImages();
//        for(ProductLine productLine : productLines){
//            System.out.println(productLine.getProductLine());
//        }

        // get product line by without image
//        List<ProductLine> productLines = productLineRepository.getProductLineWithoutImages();
//        for(ProductLine productLine : productLines){
//            System.out.println(productLine.getProductLine());
//        }

        // get product lines by partial name
//        String des = "car";
//        List<ProductLine> productLines = productLineRepository.searchProductLinesByPartialName(des);
//        for(ProductLine productLine : productLines){
//            System.out.println(productLine.getProductLine());
//        }

//        // get count products for all product line
//        Map<String, Long> result = new HashMap<>();
//        result = productLineRepository.countProductsForAllProductLine();
//        System.out.println(result);

        // find Product Lines With Products Below Stock
//        List<ProductLine> productLines = productLineRepository.findProductLinesWithProductsBelowStock(6000);
//        for(ProductLine productLine : productLines){
//            System.out.println(productLine.getProductLine());
//        }
    }
}
