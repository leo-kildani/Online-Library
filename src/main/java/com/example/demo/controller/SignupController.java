package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.LibraryManagementSystemService;

@Controller
public class SignupController {

    @Autowired
    private LibraryManagementSystemService service;

    @GetMapping("/signupForm")
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping("/signupForm")
    public String processSignupForm(RedirectAttributes redirectAttributes, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("dob") LocalDate dob) {
        if (service.getUser(username) != null) {
            redirectAttributes.addFlashAttribute("err", "Username already exists");
        } else if (service.getUserByEmail(email) != null) {
            redirectAttributes.addFlashAttribute("err", "Email already exists");
        } else {
            redirectAttributes.addFlashAttribute("msg", "User created successfully");         
            return "redirect:/loginPage";
        }
        return "redirect:/signupForm";
    }
}