package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;
import com.example.demo.entity.User;
import com.example.demo.service.LibraryManagementSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookDetailsController {
    private final LibraryManagementSystemService libService;

    @Autowired
    public BookDetailsController(LibraryManagementSystemService libService) {
        this.libService = libService;
    }

    @Autowired
    private CurrentUser currentUser;

    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @PostMapping("/rate")
    public String rateBook(@RequestParam("isbn") String isbn,
                           @RequestParam("rating") int rating) {
        User user = currentUser.getCurrentUser();
        Book book = libService.getBook(isbn);
        libService.userRatesBook(user, book, rating);
        return "redirect:/book?isbn=" + isbn;
    }

    @PostMapping("/submitReview")
    public String reviewBook(@RequestParam("isbn") String isbn,
                             @RequestParam("reviewContent") String content) {
        User user = currentUser.getCurrentUser();
        Book book = libService.getBook(isbn);
        libService.addReview(content, user, book);
        return "redirect:/book?isbn=" + isbn;
    }
}