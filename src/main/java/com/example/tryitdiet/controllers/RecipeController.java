package com.example.tryitdiet.controllers;


import com.example.tryitdiet.models.*;
import com.example.tryitdiet.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RecipeController {


    // Dependency Injection
    private final DietRepository dietRepo;
    private final RecipeRepository recipeRepo;
    private final PostRepository postRepo;
    private final IngredientRepository ingredientRepo;


    public RecipeController(DietRepository dietRepo, RecipeRepository recipeRepo, PostRepository postRepo, IngredientRepository ingredientRepo) {
        this.dietRepo = dietRepo;
        this.recipeRepo = recipeRepo;
        this.postRepo = postRepo;
        this.ingredientRepo = ingredientRepo;
    }


    // Create  Get Method for Recipe && Diets and Recipe && Ingredients
    @GetMapping("/posts/recipe")
    public String createRecipe(
            Model model
    ) {
        // add a brand new post and a brand new recipe to the model
        model.addAttribute("post", new Post());
        model.addAttribute("recipe", new Recipe());
        List<Diet> dietsList = dietRepo.findAll();
        model.addAttribute("dietsList", dietsList);
        List<Ingredient> ingredientsList = ingredientRepo.findAll();

        model.addAttribute("ingredientsList", ingredientsList);
        return "recipes/create";
    }

    // Create Post Method for Recipe && diets and Recipe && Ingredients
    @PostMapping("/posts/recipe")
    public String saveRecipe(
            @ModelAttribute Recipe recipe,
            @ModelAttribute Post post,
            @RequestParam List<Ingredient> ingredients
    ) throws ParseException {
        // Get the currently logged in user
        User author = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        // Set the currently logged in user to the newly created post/recipe
        post.setUser(author);
        recipe.setIngredients(ingredients);

        // Get the current date/time and set in to the post
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = format.format(new Date());
        Date date = format.parse(createDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        post.setDate(sqlDate);
        post.setRecipe(recipe);
        postRepo.save(post);
        return "redirect:/posts";
    }

    // Edit Recipe Get Method
    @GetMapping("/posts/recipe/edit")
    public String editRecipe(
            @RequestParam(name = "postId") long postId,
            Model model
    ) {
        Post post = postRepo.findById(postId).orElse(null);

        // This is from the template
        List<Diet> diets = post.getRecipe().getDiets();
        // This is from the database
        List<Diet> dietsList = dietRepo.findAll();


        System.out.println(post.getRecipe().getIngredients());
        model.addAttribute("recipeIngredients", post.getRecipe().getIngredients());

        model.addAttribute("post", post);
        model.addAttribute("dietsList", dietsList);
        model.addAttribute("diets",diets);
        return "recipes/edit";
    }

    // Update Recipe Post method
    @PostMapping("/posts/recipe/edit")
    public String updateRecipe(
            @ModelAttribute Post post,
            @RequestParam List<Long> diets,
            @RequestParam List<Ingredient> ingredients
    ) {
        for(Ingredient item : ingredients) {
            System.out.println(item.getId());
        }


        // update post and recipe
        List<Diet> recipeDiets = new ArrayList<>();
        for(int i= 0; i< diets.size(); i++){
            Diet thisDiet = dietRepo.getOne(diets.get(i));
            recipeDiets.add(thisDiet);
        }
        // System.out.println(recipeDiets);
        post.getRecipe().setIngredients(ingredients);
        post.getRecipe().setDiets(recipeDiets);
        // This is saving the changes in our database
        postRepo.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/ingredients.json")
    public @ResponseBody
    List<Ingredient> viewAllIngredientsInJSONFormat() {
        return ingredientRepo.findAll();
    }

    @GetMapping("/ingredients/ajax")
    public String viewAllIngredientsWithAjax() {
        return "recipes/ajax";
    }


    // test the datatable
    @PostMapping("/ingredients/test")
    public String ingredientSelection(
            @RequestParam List<Ingredient> ingredients
    ) {
        for(Ingredient item : ingredients) {
            System.out.println(item.getName());
        }
        return "redirect:/ingredients.json";
    }
}
