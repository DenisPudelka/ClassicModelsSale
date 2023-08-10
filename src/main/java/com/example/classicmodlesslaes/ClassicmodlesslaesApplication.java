package com.example.classicmodlesslaes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClassicmodlesslaesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassicmodlesslaesApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(){
        return runner -> {
            System.out.println("Im am working");
        };
    }
}
