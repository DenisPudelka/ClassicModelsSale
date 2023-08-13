package com.example.classicmodlesslaes;

import com.example.classicmodlesslaes.model.*;
import com.example.classicmodlesslaes.repository.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
            //testProductLine(productLineRepository); -- toto robi secko
            //testProduct(productRepository);
            testOffice(officeRepository);
            //testEmployee(employeeRepository, officeRepository);
            //testPayment(paymentRepository);
        };
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
//        String id = "00";
//        officeRepository.deleteOffice(id);

        // Get all offices
//        List<Office> result = officeRepository.getAllOffices();
//        for (Office office : result){
//            System.out.println(office);
//        }

        // get offices from city
//        String city = "NYC";
//        List<Office> offices = officeRepository.findOfficesByCity(city);
//        for (Office office : offices){
//            System.out.println(office);
//        }

        // get offices from country
//        String country = "USA";
//        List<Office> offices = officeRepository.findOfficesByCountry(country);
//        for (Office office : offices){
//            System.out.println(office);
//        }

        // get offices by territory
//        String territory = "NA";
//        List<Office> offices = officeRepository.findOfficesByTerritory(territory);
//        for (Office office : offices){
//            System.out.println(office);
//        }

        // get offices form phone patern
//        String phone = "44";
//        List<Office> offices = officeRepository.findOfficesWithPhonePattern(phone);
//        for (Office office : offices){
//            System.out.println(office);
//        }

        // get offices form phone patern
//        String keyword = "Street";
//        List<Office> offices = officeRepository.searchOfficesByAddress(keyword);
//        for (Office office : offices){
//            System.out.println(office);
//        }

        // get number of offices from country
//        String country = "USA";
//        System.out.println(officeRepository.countOfficesByCountry(country));

        // get list of destinct territory
//        List<String> result = officeRepository.findAllTerritories();
//        for(String s : result){
//            System.out.println(s);
//        }

        // get count Offices By Territory
//        List<Object[]> resultList = officeRepository.countOfficesByTerritory();
//        for (Object[] row : resultList) {
//            String territory = (String) row[0];
//            Long count = (Long) row[1];
//            System.out.println("Territory: " + territory + ", Count: " + count);
//        }

        // get count of emplyees in each office
//        List<Object[]> resultList = officeRepository.countEmployeesPerOffice();
//        for(Object[] row : resultList){
//            String office = (String) row[0];
//            Long count = (Long) row[1];
//            System.out.println("Office: " + office + ", Count: " + count);
//        }
    }

}
