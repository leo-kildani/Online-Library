package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookCheckout;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;

public interface LibraryManagementSystemService extends UserDetailsService {
   
    void addUser(String username, String password, String email, String firstName, String lastName, LocalDate dob);

    void updateUser(User user);

    User getUser(String username);

    List<User> getUsers();

    List<User> getUsersBySimilarUsername(String username);

    List<User> getUsersBySimilarName(String name);

    double getUserLateFees(User user);

    void deleteUser(User user);

    void addAuthor(String firstName, String lastName, String biography);

    void deleteAuthor(Author author);

    void addBook(String isbn, String title, Author author, LocalDate publishDate, String description);

    Book getBook(String isbn);

    List<Book> getBooksBySimilarTitle(String title);

    List<Book> getBooksByAuthorLastName(String authorLastName);

    List<Book> getBooksByGenre(Genre genre);

    List<Book> getBooksByPublishDate(LocalDate date);

    List<Book> getBookByAppliedFilters(String title, String authorLastName, Genre genre, LocalDate publishDate);

    int getBookStarRating(Book book);

    boolean checkBookAvailable(Book book);

    void deleteBook(Book book);

    void addBookCheckout(User user, Book book);

    void deleteBookCheckout(BookCheckout bookCheckout);

    void addReview(String content, User user, Book book);

    List<Review> getUserReviews(User user);

    List<Review> getBookReviews(Book book);

    void deleteReview(Review review);

    void addGenre(String genreName, String overview);

}
