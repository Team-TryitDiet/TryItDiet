package com.example.tryitdiet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietController {


    @GetMapping("/diets/dairy-free")
    public String showDairyFreePage() {
        return "diets/dairy-free";
    }

    @GetMapping("/diets/gluten-free")
    public String showGlutenFreePage() {
        return "diets/gluten-free";
    }

    @GetMapping("/diets/high-protein")
    public String showHighProteinPage() {
        return "diets/high-protein";
    }

    @GetMapping("/diets/keto")
    public String showKetoPage() {
        return "diets/keto";
    }

    @GetMapping("/diets/paleo")
    public String showPaleoPage() {
        return "diets/paleo";
    }

    @GetMapping("/diets/pescatarian")
    public String showPescatarianPage() {
        return "diets/pescatarian";
    }

    @GetMapping("/diets/mediterranean")
    public String showMediterraneanPage() {
        return "diets/mediterranean";
    }

    @GetMapping("/diets/vegan")
    public String showVeganPage() {
        return "diets/vegan";
    }

    @GetMapping("/diets/vegetarian")
    public String showVegetarianPage() {
        return "diets/vegetarian";
    }
}
