package com.example.tryitdiet.controllers;


import com.example.tryitdiet.models.Post;
import com.example.tryitdiet.models.Recipe;

import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.PostRepository;
import com.example.tryitdiet.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.channels.ScatteringByteChannel;
import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder, PostRepository postRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    // Welcome page
    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    //    get method for register an account
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    //  post method for register an account
    @PostMapping("/register")
    public String register(@ModelAttribute User user,
                           Model model,
                           @RequestParam(name = "confirmPassword") String confirmPassword
            , @RequestParam(name = "password") String password
    ) {

        if (userRepo.findAllByUsername(user.getUsername()).size() > 0) {
            model.addAttribute("errorUserName", "Please choose another username");
            return "users/register";
        }

        if (!password.equals(confirmPassword)) {
            return "users/register";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        if (user.getId() == 0) {
            userRepo.save(user);
            return "redirect:/login";
        } else {
            userRepo.save(user);
            return "users/profile";
        }
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "users/login";
    }


    @GetMapping("/profile")
    public String profilePage(Model model) {
        User getUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getOne(getUser.getId());
        model.addAttribute("user", currentUser);
        List<Post> posts = currentUser.getPosts();
        model.addAttribute("photoUrl", currentUser.getProfilePic());
        model.addAttribute("posts", posts);
        return "users/profile";
    }

    @PostMapping("/profile/pic")
    public String saveUserProfile(@RequestParam String url, @ModelAttribute User user) {
        User getUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepo.getOne(getUser.getId());
        currentUser.setProfilePic(url);
        userRepo.saveAndFlush(currentUser);
        return "redirect:/profile";
    }

    @GetMapping("/user/edit")
    public String editUserInformation(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Post> posts=currentUser.getPosts();
        model.addAttribute("user", userRepo.findById(currentUser.getId()).orElse(null));
//        model.addAttribute("profilePic", currentUser.getProfilePic());
        model.addAttribute("posts", posts);
        return "users/editProfile";
    }
    @PostMapping("/user/edit")
    public String editUserInfoDone(
                                   Model model,
                                   @RequestParam(name="username") String username,
                                   @RequestParam(name="email") String email,
                                   @RequestParam(name = "confirmPassword") String confirmPassword,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name="phone_name") String phone_number){
        User userPrinciple = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.getOne(userPrinciple.getId());
        if(!user.getUsername().equals(username)) {
            if (userRepo.findAllByUsername(username).size() > 0) {
                model.addAttribute("errorUserName", "Please choose another username");
                return "users/editProfile";
            }
        }
        if (!password.equals(confirmPassword)) {
            return "users/editProfile";
        }
        if(!user.getEmail().equals(email)){
            if(userRepo.findAllByEmail(email).size()>0){
                model.addAttribute("errorEmail","Please choose another email");
                return "users/editProfile";
            }
        }
        user.setUsername(username);
        user.setEmail(email);
        String hash = passwordEncoder.encode(password);
        user.setPassword(hash);
        user.setPhone_number(phone_number);

        userRepo.save(user);
        return "redirect:/profile";
    }
}
