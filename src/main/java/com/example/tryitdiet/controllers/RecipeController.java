package com.example.tryitdiet.controllers;


import com.example.tryitdiet.models.*;
import com.example.tryitdiet.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    // Create Recipe and diets Get Method
    @GetMapping("/posts/recipe")
    public String createRecipe(
            Model model,
            @RequestParam(value = "search", required = false) String search
    ) {
        // add a brand new post and a brand new recipe to the model
        model.addAttribute("post", new Post());
        model.addAttribute("recipe", new Recipe());
        List<Diet> dietsList = dietRepo.findAll();
        model.addAttribute("dietsList", dietsList);
        List<Ingredient> ingredientsList = ingredientRepo.findAll();

        if (search != null) {
            ingredientsList = ingredientRepo.findByNameContaining(search);
        }

        model.addAttribute("ingredientsList", ingredientsList);
        return "recipes/create";
    }

    // Create Recipe and diets Post Method
    @PostMapping("/posts/recipe")
    public String saveRecipe(
            @ModelAttribute Recipe recipe,
            @ModelAttribute Post post,
            @RequestParam List<Diet> diets,
            @RequestParam(value = "ingredients") List<Ingredient> ingredients
    ) throws ParseException {
        // Get the currently logged in user
        User author = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        // Set the currently logged in user to the newly created post/recipe

        post.setUser(author);

        // Get the current date/time and set in to the post
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = format.format(new Date());
        Date date = format.parse(createDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        post.setDate(sqlDate);

        // save the post and set to recipe post property
        Post postRecipe = postRepo.saveAndFlush(post); // grab the newly saved post/recipe

        // set diet(s) to the recipe
        if (!diets.isEmpty()) {
            recipe.setPost(postRecipe);
        }

        // save the recipe and set the post recipe property
        Recipe savedRecipe = recipeRepo.saveAndFlush(recipe); // grab the newly saved recipe
        postRecipe.setRecipe(savedRecipe);
        postRepo.save(postRecipe); // update post with recipe information

        return "redirect:/posts";
    }

    // Edit Recipe Get Method
    @GetMapping("/posts/recipe/edit")
    public String editRecipe(
            @RequestParam(name = "postId") long postId,
            Model model
    ) {
        Post post = postRepo.findById(postId).orElse(null);
        model.addAttribute("post", post);
        return "recipes/edit";
    }

    // Update Recipe Post method
    @PostMapping("/posts/recipe/edit")
    public String updateRecipe(
            @ModelAttribute Post post
    ) {
        // update post and recipe
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
            @RequestParam List<Ingredient> list
    ) {
        for(Ingredient item : list) {
            System.out.println(item);
        }
        return "redirect:/ingredients.json";
    }
}
