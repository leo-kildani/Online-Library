package com.example.demo.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.User;
import com.example.demo.service.LibraryManagementSystemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private LibraryManagementSystemService service;

    @GetMapping("/loginPage")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/loginPage")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        User foundUser = service.getUser(username);
        if (foundUser != null && foundUser.getPassword().trim().equals(password.trim())) {
            currentUser.setCurrentUser(foundUser);
            return "redirect:/userprofile";
        } else {
            redirectAttributes.addFlashAttribute("err", "Invalid Username or Password");
            return "redirect:/loginPage";
        }
    }
}
