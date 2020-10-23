package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {

    // Dependency Injection
    private final PostRepository postRepo;

    // PostController Constructor
    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    // Create Post Get Method
    @GetMapping("/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    // Create Post Method
    @PostMapping("/create")
    public String savePost(@ModelAttribute Post post) throws ParseException {

        if (post.getId() == 0) {
            // New Post
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

        // save Post in the database
        postRepo.save(post);

        return "redirect:/posts";
    }

    // Read Posts Method
    @GetMapping("/posts")
    public String showAllPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

    // Show individual Post Method
    @GetMapping("/posts/{id}")
    public String showSinglePost(@PathVariable(name = "id") long id, Model model) {
        Post post = postRepo.findById(id).orElse(null);
        model.addAttribute("post", post);
        return "posts/show";
    }

    // Edit Post Method
    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable(name = "id") long id, Model model) {
        // Get the currently selected post
        Post post = postRepo.findById(id).orElse(null);

        // Add the selected post to the model to allow us to
        // put the post's content onto the page
        model.addAttribute("post", post);
        return "posts/edit";
    }

    // Delete Post Method
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable(name = "id") long id) {
        // remove the currently selected post
        Post post = postRepo.findById(id).orElse(null);
        postRepo.delete(post);
        return "redirect:/posts";
    }
}
