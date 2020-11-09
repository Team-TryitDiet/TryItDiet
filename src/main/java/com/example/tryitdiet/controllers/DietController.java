package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.models.Ingredient;
import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.repositories.DietRepository;
import com.example.tryitdiet.repositories.IngredientRepository;
import com.example.tryitdiet.repositories.PostRepository;
import com.example.tryitdiet.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;

@Controller
public class DietController {

    private final DietRepository dietRepo;
    private final PostRepository postRepo;
    private final IngredientRepository ingredientRepo;
    private final RecipeRepository recipeRepo;

    public DietController(DietRepository dietRepo, PostRepository postRepo, RecipeRepository recipeRepo, IngredientRepository ingredientRepo) {
        this.dietRepo = dietRepo;
        this.postRepo = postRepo;
        this.recipeRepo = recipeRepo;
        this.ingredientRepo = ingredientRepo;
    }

//    dairy-free
//    gluten-free
//    high-protein
//    keto
//    paleo
//    pescatarian
//    mediterranean
//    vegan
//    vegetarian
//    other


    @GetMapping("/diets/diet-index")
    public String showDietIndexPage() {
        return "diets/diet-index";
    }

    @GetMapping("/diets/dairy-free")
    public String showDairyFreePage(
            @RequestParam(value = "search", required = false) String search,
            Model model) {
        Diet df = dietRepo.findDietByTitle("dairy-free");
        List<Post> dfPosts = df.getPosts();

        // If search is not empty
        if (search != null) {
            // check to see if search term(s) is/has:
            // 1. ingredients
            // 3. title
            String[] splitSearch = search.split("[,.?!\\t\\n ]+");

            // if search string is not just empty spaces, commas, etc.
            if (splitSearch.length > 0) {

                // loop through dairy-free posts and grab all recipes and ingredients
                HashSet<Ingredient> dfIngredients = new HashSet<>();
                for(Post post : dfPosts) {
                    if (post.getRecipe() != null) {
                        dfIngredients.addAll(post.getRecipe().getIngredients());
                    }
                }

                // loop through search term
                for(int i = 0; i < splitSearch.length; i++) {

                    // check if search term is a post title
//                    if(dfRecipes.contains(recipeRepo.findBy)) {}
                }

            }
        }


        model.addAttribute("controller", "/diets/dairy-free");
        model.addAttribute("dfPosts", dfPosts);
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
