package com.example.tryitdiet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietController {


    @GetMapping("/diets/dairy-free")
    public String showDairyFreePage() {
        return "diets/dairy-free";
    }
}
