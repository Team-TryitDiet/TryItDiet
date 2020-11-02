package com.example.tryitdiet.controllers;
import com.example.tryitdiet.models.User;
import com.example.tryitdiet.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public String welcome() {
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam(name = "confirmPassword") String confirmPassword, @RequestParam(name = "password") String password) {
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

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "users/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model) {
User getUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser =  userRepo.getOne(getUser.getId());
        model.addAttribute("user", currentUser);

        model.addAttribute("photoUrl", currentUser.getProfilePic());
        return "users/profile";
    }

    @PostMapping("/profile/pic")
    public String saveUserProfile(@RequestParam String url, @ModelAttribute User user) {
        User getUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser =  userRepo.getOne(getUser.getId());


        currentUser.setProfilePic(url);
        userRepo.save(currentUser);
        return "redirect:/profile";
    }

    @GetMapping("/user/edit")
    public String editUserInformation(@ModelAttribute User user, Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userRepo.findById(currentUser.getId()).orElse(null));
        return "users/register";

    }

//    @PostMapping("/user/edit")
//    public String saveUserInformation(@RequestParam String avatarurl) {
//        //instantiate a user repo and save url as user's photo
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User currentUser = userRepo.findById(user.getId()).orElse(null);
//        currentUser.setProfilePic(avatarurl);
//        userRepo.save(currentUser);
//        return "redirect:/profile";
//    }
}
