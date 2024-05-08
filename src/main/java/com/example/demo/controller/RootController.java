package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @Autowired
    private CurrentUser currentUser;

    @GetMapping("/")
    public String redirectToLogin() {
        currentUser.setCurrentUser(null);
        return "redirect:/loginPage";
    }
}
