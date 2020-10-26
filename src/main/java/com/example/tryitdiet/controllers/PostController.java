package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.Comment;
import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.CommentRepository;
import com.example.tryitdiet.repositories.PostRepository;
import com.example.tryitdiet.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    // Dependency Injection
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final CommentRepository commentRepo;

    // PostController Constructor
    public PostController(PostRepository postRepo, UserRepository userRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
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
        List <Comment> comments = post.getComments();
        model.addAttribute("allComments",comments);
        model.addAttribute("post", post);
        // add a new comment object to the model
        model.addAttribute("newComment", new Comment());
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

    // Create new comment
    @PostMapping("posts/comment")
    public String createComment(@RequestParam(name = "postId") long postId, @ModelAttribute Comment newComment){

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User currentUser = userRepo.findById(user.getId()).orElse(null);
        Post currentPost = postRepo.findById(postId).orElse(null);
        newComment.setUser(currentUser);
        newComment.setPost(currentPost);
        commentRepo.save(newComment);

        return "redirect:/posts/" + postId;
    }

//    edit the comment
    @GetMapping("/comments/edit")
    public String passingInfoEditComment(@RequestParam(name="commentId")long commentId,
                                         @RequestParam(name="postId") long postId, Model model){
        Post post = postRepo.getOne(postId);
        Comment comment = commentRepo.getOne(commentId);
        model.addAttribute("post",post);
        model.addAttribute("comment",comment);
        return "comments/editComment";
    }

    @PostMapping("/comments/edit")
    public String editComment(@ModelAttribute Comment comment,
                              @RequestParam(name="body") String body){
        comment.setBody(body);
        commentRepo.save(comment);
        return "redirect:/posts/" + comment.getPost().getId();

    }

    // Delete a comment
    @PostMapping("/comments/delete")
    public String deleteComment(
            @RequestParam(name = "commentId") long commentId,
            @RequestParam(name = "postId") long postId
    ){
        System.out.println(commentId);
        commentRepo.deleteById(commentId);
        return "redirect:/posts/" + postId ;
    }


}
