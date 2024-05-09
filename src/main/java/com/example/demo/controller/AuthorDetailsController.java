package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.LibraryManagementSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AuthorDetailsController {
    private final LibraryManagementSystemService libService;

    @Autowired
    public AuthorDetailsController(LibraryManagementSystemService libService) {
        this.libService = libService;
    }

    @Autowired
    private CurrentUser currentUser;

    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

//    @GetMapping("/catalog")
//    public String loadPage(Model model) {
//        User user = currentUser.getCurrentUser();
//
//        if (user != null) {
//            model.addAttribute("user", user);
//            //            model.addAttribute("userRole", libService.findByUsername(user.getUsername()));
//        } else {
//            return "redirect:/loginPage";
//        }
//
//
//
//        return "catalog";
//    }
}