package com.example.tryitdiet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietController {


    @GetMapping("/login")
    public String showLoginPage() {
        return "users/login";
    }
}
