package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.Book;
import com.example.demo.service.LibraryManagementSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CatalogController {

    @Autowired
    private LibraryManagementSystemService service;

    @Autowired
    private CurrentUser currentUser;

    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @GetMapping("/catalog")
    public String getUserProfile(Model model) {
        User user = currentUser.getCurrentUser();

        if (user != null) {
            model.addAttribute("user", user);
            // model.addAttribute("userRole",
            // libService.findByUsername(user.getUsername()));
        } else {
            return "redirect:/loginPage";
        }

        return "catalog";
    }

    @GetMapping("/catalog/search")
    public String searchBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre,
            Model model) {

        // convert empty genre string to null
        genre = (genre != null && !genre.isEmpty()) ? genre : null;

        List<Book> books = service.getBookByAppliedFilters(title, author, genre);
        model.addAttribute("books", books);
        logger.info("Retrieved Books: " + books);
        return "catalog";
    }

    @GetMapping("/catalog/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully");
        return "redirect:/loginPage";
    }
}