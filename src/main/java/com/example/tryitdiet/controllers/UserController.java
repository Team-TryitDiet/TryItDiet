package com.example.tryitdiet.controllers;

import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/")
//    @ResponseBody
    public String welcome() {
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "users/login";
    }

    @PostMapping("/user/{id}")
    public String profilePage(@PathVariable long id, Model model){
        model.addAttribute("user", userRepo.getOne(id));
        return "users/profile";
    }






}
