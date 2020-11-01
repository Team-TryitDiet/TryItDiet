package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.repositories.DietRepository;
import com.example.tryitdiet.repositories.IngredientRepository;
import com.example.tryitdiet.repositories.PostRepository;
import com.example.tryitdiet.repositories.RecipeRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DietController {
    private DietRepository dietRepo;


    public DietController(DietRepository dietRepo, PostRepository postRepo, IngredientRepository ingredientRepo) {
        this.dietRepo = dietRepo;

    }

    @GetMapping("/diets/dairy-free")
    public String showDairyFreePage(Model model) {
        if ("dairy-free" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("dairy-free");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/dairy-free";
    }

    @GetMapping("/diets/gluten-free")
    public String showGlutenFreePage(Model model) {
        if ("gluten-free" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("gluten-free");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/gluten-free";
    }

    @GetMapping("/diets/high-protein")
    public String showHighProteinPage(Model model) {
        if ("high-protein" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("high-protein");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/high-protein";
    }

    @GetMapping("/diets/keto")
    public String showKetoPage(Model model) {
        if ("keto" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("keto");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/keto";
    }

    @GetMapping("/diets/paleo")
    public String showPaleoPage(Model model) {
        if ("paleo" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("paleo");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/paleo";
    }

    @GetMapping("/diets/pescatarian")
    public String showPescatarianPage(Model model) {
        if ("pescatarian" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("pescatarian");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/pescatarian";
    }

    @GetMapping("/diets/mediterranean")
    public String showMediterraneanPage(Model model) {
        if ("mediterranean" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("mediterranean");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/mediterranean";
    }

    @GetMapping("/diets/vegan")
    public String showVeganPage(Model model) {
        if ("vegan" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("vegan");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/vegan";
    }

    @GetMapping("/diets/vegetarian")
    public String showVegetarianPage(Model model) {
        if ("vegetarian" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("vegetarian");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/vegetarian";
    }

    @GetMapping("/diets/other")
    public String showOtherPage(Model model) {
        if ("other" != null) {
            List<Diet> diets = dietRepo.findByTitleContaining("other");
            System.out.println(diets);
            model.addAttribute("diets", diets);
        }
        return "diets/other";
    }
}
