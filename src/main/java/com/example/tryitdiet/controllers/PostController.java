package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.*;
import com.example.tryitdiet.repositories.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class PostController {

    // Dependency Injection
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final CommentRepository commentRepo;
    private final DietRepository dietRepo;
    private final RecipeRepository recipeRepo;
    private final IngredientRepository ingredientRepo;

    public PostController(PostRepository postRepo, UserRepository userRepo, CommentRepository commentRepo, RecipeRepository recipeRepo, DietRepository dietRepo, RecipeRepository recipeRepo1, IngredientRepository ingredientRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
        this.dietRepo = dietRepo;
        this.recipeRepo = recipeRepo1;
        this.ingredientRepo = ingredientRepo;
    }

    // Get Method to create New Post and Diets
    @GetMapping("/create")
    public String createPost(
            Model model
    ) {
        List<Diet> dietsList = dietRepo.findAll();
        model.addAttribute("post", new Post());
        model.addAttribute("dietsList", dietsList);
        return "posts/create";
    }

    // Create Post Method for Creating/Editing Post && Diets
    @PostMapping("/create")
    public String savePost(
            @ModelAttribute @Valid Post post,
            Errors validation,
            Model model
    ) throws ParseException {


        if(post.getDescription().equals("")){
            validation.rejectValue(
                    "description",
                    "post.description",
                    "Description cannot be empty"
            );
        }
        if(validation.hasErrors()){
            model.addAttribute("error", validation);
            model.addAttribute("post",post);
            return "posts/create";
        }
        // Set the User in the Post for the New Post
        if (post.getId() == 0) {
            User author = (User) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            // Set the currently logged in user to the newly created post
            post.setUser(author);

            // Get the current date/time and set in to the post
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String createDate = format.format(new Date());
            Date date = format.parse(createDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            post.setDate(sqlDate);
        }
        // Save Post in the database
        postRepo.save(post);

        return "redirect:/posts";
    }

    // Read Posts Method
    @GetMapping("/posts")
    public String showAllPosts(
            Model model,
            @RequestParam(value = "search", required = false) String search
    ) {

        long currentUserId = 0;
        if ( !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            currentUserId = user.getId();
        }
        model.addAttribute("user_id", currentUserId);
         List<Post> allPost = postRepo.findAll();

        // If search is not empty
        if (search != null) {
            // check to see if search term(s) is/has:
            // 1. ingredients
            // 2. diets
            // 3. title
            String[] splitSearch = search.split("[,.?!\\t\\n ]+");

            // if search string is not just empty spaces, commas, etc.
            if (splitSearch.length > 0) {

                // declare aa Ingredient List collection for
                List<Ingredient> ingredients = new ArrayList<>();
                List<Diet> diets = new ArrayList<>();
                List<Recipe> recipes = new ArrayList<>();
                allPost.clear();

                // Build list of ingredients and/or diets and
                for (int i = 0; i < splitSearch.length; i++) {
                    if (ingredientRepo.findIngredientByName(splitSearch[i]) != null) {
                        ingredients.add(ingredientRepo.findIngredientByName(splitSearch[i]));
                    }
                    if (dietRepo.findDietByTitle(splitSearch[i]) != null) {
                        diets.add(dietRepo.findDietByTitle(splitSearch[i]));
                    }
                    if (!postRepo.findByTitleContaining(splitSearch[i]).isEmpty()) {
                        allPost.addAll(postRepo.findByTitleContaining(splitSearch[i]));
                    }
                }

                // if ingredients is not empty
                if (!ingredients.isEmpty()) {
                    recipes.addAll(recipeRepo.findAllByIngredientsIn(ingredients));
                }
                // if diets is not empty
                if (!diets.isEmpty()) {
                    allPost.addAll(postRepo.findAllByDiets(diets));
                    recipes.addAll(recipeRepo.findAllByDiets(diets));
                }

                // loop through recipes if it is not empty
                if (!recipes.isEmpty()) {
                    for (Recipe recipe : recipes) {
                        allPost.add(recipe.getPost());
                    }
                }
            }
        }

        model.addAttribute("controller", "/posts");
        model.addAttribute("posts", new HashSet<>(allPost));
        return "posts/index";
    }


    // Show individual Post and all the comments for this post Method
    @GetMapping("/posts/{id}")
    public String showSinglePost(
            @PathVariable(name = "id") long id,
            Model model
    ) {

        long currentUserId = 0;
        if ( !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) ) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            currentUserId = user.getId();
            model.addAttribute("user",userRepo.findById(currentUserId).orElse(null));
        }
        Post post = postRepo.findById(id).orElse(null);
        List<Comment> comments = post.getComments();

        // Check the post is from Regular Post or Recipe Post
        if(post.getRecipe() != null) {
            List<Diet> diets = post.getRecipe().getDiets();
            model.addAttribute("diets", diets);
        } else{
            List<Diet> diets = post.getDiets();
            model.addAttribute("diets", diets);
        }
        model.addAttribute("allComments", comments);
        model.addAttribute("post", post);
        model.addAttribute("user_id", currentUserId);

        // Add a new comment object to the model
        model.addAttribute("newComment", new Comment());
        return "posts/show";
    }

    // Edit Post Method the view button on Post index page
    @GetMapping("/posts/{id}/edit")
    public String editPost(
            @PathVariable(name = "id") long id,
            Model model
    ) {
        // Get the currently selected post
        Post post = postRepo.findById(id).orElse(null);
        List<Diet> dietsList = dietRepo.findAll();
        // Add the selected post to the model to allow us to
        // put the post's content onto the page
        model.addAttribute("post", post);
        model.addAttribute("dietsList", dietsList);
        return "posts/edit";
    }

    // Delete Post Method
    @GetMapping("/posts/delete/{id}")
    public String deletePost(
            @PathVariable(name = "id") long id
    ) {
        // Remove the currently selected post
        Post post = postRepo.findById(id).orElse(null);
        postRepo.delete(post);
        return "redirect:/posts";
    }

    // Create new comment
    @PostMapping("posts/comment")
    public String createComment(
            @RequestParam(name = "postId") long postId,
            @ModelAttribute Comment newComment
    ) {


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userRepo.findById(user.getId()).orElse(null);
        Post currentPost = postRepo.findById(postId).orElse(null);
        newComment.setUser(currentUser);
        newComment.setPost(currentPost);
        commentRepo.save(newComment);

        return "redirect:/posts/" + postId;
    }

    // Edit the comment
    @GetMapping("/comments/edit")
    public String passingInfoEditComment(
            @RequestParam(name = "commentId") long commentId,
            @RequestParam(name = "postId") long postId, Model model
    ) {
        Post post = postRepo.getOne(postId);
        Comment comment = commentRepo.getOne(commentId);
        model.addAttribute("post", post);
        model.addAttribute("comment", comment);
        return "comments/editComment";
    }

    @PostMapping("/comments/edit")
    public String editComment(
            @ModelAttribute Comment comment
    ) {
        commentRepo.save(comment);
        return "redirect:/posts/" + comment.getPost().getId();

    }

    // Delete a comment
    @PostMapping("/comments/delete")
    public String deleteComment(
            @RequestParam(name = "commentId") long commentId,
            @RequestParam(name = "postId") long postId
    ) {
        commentRepo.deleteById(commentId);
        return "redirect:/posts/" + postId;
    }


}
