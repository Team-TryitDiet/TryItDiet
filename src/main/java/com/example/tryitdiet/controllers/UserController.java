package com.example.tryitdiet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {


    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        return "<h1>This string is supposed to be the index page</h1>";

    }

}
