    package com.example.demo.controller;

    import com.example.demo.entity.User;
    import com.example.demo.entity.Book;
    import com.example.demo.entity.Genre;
    import com.example.demo.entity.Author;
    import com.example.demo.service.LibraryManagementSystemService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
    import java.util.ArrayList;
    import java.util.Optional;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    @Controller
    public class CatalogController {
        
        @Autowired
        private LibraryManagementSystemService libService;


        @Autowired
        private CurrentUser currentUser;

        private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

        @GetMapping("/catalog")
        public String loadPage(Model model) {
            User user = currentUser.getCurrentUser();

            if (user != null) {
                model.addAttribute("user", user);
    //            model.addAttribute("userRole", libService.findByUsername(user.getUsername()));
            } else {
                return "redirect:/loginPage";
            }

            List<Genre> genres = libService.getAllGenres();
            logger.info("retrieved genres:" + genres);
            model.addAttribute("genres", genres);

            return "catalog";
        }

        @GetMapping("/catalog/search")
        public String searchBooks(@RequestParam(required = false) Optional<String> title,
                                  @RequestParam(required = false) Optional<String> author,
                                  @RequestParam(required = false) Optional<String> genre,
                                  Model model) {
            List<Book> books = libService.getBookByAppliedFilters(
                    title.orElse(null),
                    author.orElse(null),
                    genre.orElse(null));

            model.addAttribute("books", books);
            logger.info("Retrieved Books: " + books);

            List<Genre> genres = libService.getAllGenres();
            logger.info("retrieved genres:" + genres);
            model.addAttribute("genres", genres);

            List<Genre> resultGenres = new ArrayList<Genre>();
            List<Author> resultAuthors = new ArrayList<Author>();
            books.forEach(book -> {
                resultGenres.add(libService.getGenreByBook(book).get(0));
                resultAuthors.add(libService.getAuthorByBook(book));
            });
            model.addAttribute("resultGenres", resultGenres);
            model.addAttribute("resultAuthors", resultAuthors);

            return "catalog";
        }

        @GetMapping("/author")
        public String authorDetails(@RequestParam("id") int authorId, Model model) {
            Optional<Author> authorOptional = libService.getAuthorById(authorId);
            if (!authorOptional.isPresent()) {
                return "redirect:/catalog";
            }
            Author author = authorOptional.get();
            model.addAttribute("author", author);

            List<Book> booksByAuthor = libService.getBookByAuthorId(authorId);
            model.addAttribute("books", booksByAuthor);

            List<Genre> bookGenres = new ArrayList<Genre>();
            booksByAuthor.forEach(book -> {
                bookGenres.add(libService.getGenreByBook(book).get(0));
            });
            model.addAttribute("bookGenres", bookGenres);
            logger.info("bookGenres:" + bookGenres);

            return "authorDetails"; // Name of the Thymeleaf template for author details
        }

        @GetMapping("/catalog/logout")
        public String logout(RedirectAttributes redirectAttributes) {
            redirectAttributes.addFlashAttribute("logoutMessage", "You have been logged out successfully");
            return "redirect:/loginPage";
        }
    }