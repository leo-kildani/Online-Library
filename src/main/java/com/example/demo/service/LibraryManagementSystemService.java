package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookCheckout;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;

public interface LibraryManagementSystemService {
   
    void addUser(String username, String password, String email, String firstName, String lastName, LocalDate dob, String role);

    void updateUser(User user);

    User getUser(String username);

    List<User> getUsers();

    List<User> getUsersBySimilarUsername(String username);

    List<User> getUsersBySimilarName(String name);

    double getUserLateFees(User user);

    void deleteUser(User user);

    void addAuthor(String firstName, String lastName, String biography);

    List<Author> getUserFavoriteAuthor(User user);

    void userFavoritesAuthor(User user, Author author);

    void userUnfavoritesAuthor(User user, Author author);

    void updateAuthor(Author author);

    void deleteAuthor(Author author);

    void addBook(String isbn, String title, Author author, LocalDate publishDate, String description, List<Genre> genres);

    void updateBook(Book book);

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

    void updateReview(Review review);

    List<Review> getUserReviews(User user);

    List<Review> getBookReviews(Book book);

    void deleteReview(Review review);

    void addGenre(String genreName, String overview);

    List<Genre> getUserLikedGenres(User user);

    void userLikesGenre(User user, Genre genre);

    void userUnlikesGenre(User user, Genre genre);

    void deleteGenre(Genre genre);
}
