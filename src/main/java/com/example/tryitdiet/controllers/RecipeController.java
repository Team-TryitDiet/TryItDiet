package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.Diet;
import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.models.Recipe;
import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.DietRepository;
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



    private final DietRepository dietRepo;
    // Dependency Injection
    private final RecipeRepository recipeRepo;
    private final PostRepository postRepo;

//    // RecipeController Constructor
//    public RecipeController(RecipeRepository recipeRepo, PostRepository postRepo) {
//        this.recipeRepo = recipeRepo;
//        this.postRepo = postRepo;
//    }
public RecipeController(DietRepository dietRepo, RecipeRepository recipeRepo, PostRepository postRepo) {
    this.dietRepo = dietRepo;
    this.recipeRepo = recipeRepo;
    this.postRepo = postRepo;
}

    // Create Recipe and diets Get Method
    @GetMapping("/posts/recipe")
    public String createRecipe(Model model) {
        // add a brand new post and a brand new recipe to the model
        model.addAttribute("post", new Post());
        model.addAttribute("recipe", new Recipe());
        List<Diet> dietsList = dietRepo.findAll();
        model.addAttribute("dietsList",dietsList);
        return "recipes/create";
    }
    //create Diets for recipe Post Method
    @PostMapping("/recipe/diets/create")
    public String createDiets(@RequestParam(name = "diet") List<Diet> diets,
                              @RequestParam(name = "checked") List<Boolean> checked,@ModelAttribute Recipe recipe){
        List<Diet> diets1 = new ArrayList<>();
        for(int i =0; i < diets.size();i++){
            if(checked.get(i)){
                diets1.add(diets.get(i));
            }
        }
        recipe.setDiets(diets1);
        recipeRepo.save(recipe);
        return "recipes/create";
    }

    // Create Recipe Post Method
    @PostMapping("/posts/recipe")
    public String saveRecipe(
            @ModelAttribute Post post,
            @ModelAttribute Recipe recipe
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
        recipe.setPost(postRecipe);

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
//        Recipe recipe = recipeRepo.findById(post.getRecipe().getId()).orElse(null);
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


}
