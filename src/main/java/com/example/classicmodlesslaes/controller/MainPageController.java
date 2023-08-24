package com.example.classicmodlesslaes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }
}
