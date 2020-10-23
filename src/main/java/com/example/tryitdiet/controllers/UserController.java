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
//    @ResponseBody
    public String welcome() {
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user,@RequestParam(name="confirmPassword") String confirmPassword, @RequestParam(name="password") String password) {
        if (!password.equals(confirmPassword)) {
            return "users/register";
        }
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        if(user.getId()==0){
            userRepo.save(user);
            return "redirect:/login";
        }else {
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
    public String profilePage( Model model){
        model.addAttribute("user", (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "users/profile";
    }


    @GetMapping("/user/edit")
    public String editUserInformation(@ModelAttribute User user,Model model){
        model.addAttribute("user",(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "users/register";
    }

}
