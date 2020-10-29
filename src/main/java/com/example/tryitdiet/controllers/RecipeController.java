package com.example.tryitdiet.controllers;


import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.models.Recipe;
import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.DietRepository;
import com.example.tryitdiet.models.Ingredient;
import com.example.tryitdiet.repositories.IngredientRepository;
import com.example.tryitdiet.repositories.PostRepository;
import com.example.tryitdiet.repositories.RecipeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    // Create Recipe and diets Get Method
    @GetMapping("/posts/recipe")
    public String createRecipe(Model model) {
        // add a brand new post and a brand new recipe to the model
        model.addAttribute("post", new Post());
        model.addAttribute("recipe", new Recipe());
        List<Diet> dietsList = dietRepo.findAll();
        model.addAttribute("dietsList", dietsList);
        model.addAttribute("ingredientsList", ingredientRepo.findAll());
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

        for(Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName());
        }

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
        //this is from the template
        List<Diet> diets = post.getRecipe().getDiets();
        //this is from database
        List<Diet> dietsList =dietRepo.findAll();
        model.addAttribute("post", post);
        model.addAttribute("dietsList", dietsList);
        model.addAttribute("diets",diets);
        return "recipes/edit";
    }

    // Update Recipe Post method
    @PostMapping("/posts/recipe/edit")
    public String updateRecipe(
            @ModelAttribute Post post,
            @RequestParam List<Long> diets
    ) {
        // update post and recipe
        List<Diet> recipeDiets = new ArrayList<>();
        for(int i= 0; i< diets.size(); i++){
            Diet thisDiet = dietRepo.getOne(diets.get(i));
            recipeDiets.add(thisDiet);
        }
//        System.out.println(recipeDiets);
        post.getRecipe().setDiets(recipeDiets);
        //this is saving the changes in our database
        postRepo.save(post);
        return "redirect:/posts";
    }


}
