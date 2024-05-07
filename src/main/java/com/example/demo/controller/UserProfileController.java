package com.example.demo.controller;

import com.example.demo.entity.CurrentUser;
import com.example.demo.service.LibraryManagementSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.demo.entity.User;

@Controller
public class UserProfileController {
    
    private final LibraryManagementSystemService libService;
    
    @Autowired
    public UserProfileController(LibraryManagementSystemService libService) {
        this.libService = libService;
    }

    @GetMapping("/userprofile")
    public String getUserProfile(Model model) {
        User currentUser = CurrentUser.getInstance().getUser();

        if (currentUser != null) {
            model.addAttribute("favoriteAuthors", libService.getUserFavoriteAuthor(currentUser));
            model.addAttribute("likedGenres", libService.getUserLikedGenres(currentUser));
            model.addAttribute("recommendations", libService.getBooksBySimilarTitle("exampleTitle"));
        } else {
            return "redirect:/login";
        }

        return "userprofile";
    }
}
