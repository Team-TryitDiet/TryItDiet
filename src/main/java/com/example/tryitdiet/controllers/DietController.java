package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.repositories.DietRepository;
import com.example.tryitdiet.repositories.PostRepository;
import com.example.tryitdiet.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
