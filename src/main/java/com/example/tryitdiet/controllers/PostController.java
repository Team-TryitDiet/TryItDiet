package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.Comment;
import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.CommentRepository;
import com.example.tryitdiet.repositories.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    // Dependency Injection
    private final PostRepository postRepo;
    private final CommentRepository commentRepo;

    // PostController Constructor
    public PostController(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
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

    // Show individual Post and all the comments for this post Method
    @GetMapping("/posts/{id}")
    public String showSinglePost(@PathVariable(name = "id") long id, Model model) {
        Post post = postRepo.findById(id).orElse(null);
        List <Comment> comments = postRepo.getOne(id).getComments();
        model.addAttribute("comments",comments);
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

    //create new comment
    @PostMapping("posts/{id}/comment")
    public String createComment(@ModelAttribute Post post, @RequestParam(name = "body") String body){
        Comment comment = new Comment();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setBody(body);
        comment.setUser(user);
        comment.setPost(post);
        commentRepo.saveAndFlush(comment);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/comments/edit")
    public String editCommentGet(@ModelAttribute Comment comment, Model model){
        model.addAttribute(("comment"), commentRepo.getOne(comment.getId()));
        return "comments/editComment";
    }

    @PostMapping("/comments/{id}/edit")
    public String editComment(@ModelAttribute Comment comment,@RequestParam(name = "body") String body){
        commentRepo.getOne(comment.getId());
        comment.setBody(body);
        commentRepo.save(comment);
        return "redirect:/posts/" + commentRepo.getOne(comment.getId()).getPost().getId();
    }

    @GetMapping("/comments/delete")
    public String deleteComment(@ModelAttribute Comment comment ){
        long id1 = comment.getPost().getId();
        commentRepo.delete(comment);
        return "redirect:/posts/" + id1 ;
    }


}
