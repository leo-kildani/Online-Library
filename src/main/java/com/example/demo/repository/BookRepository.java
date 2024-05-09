package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.entity.Author;

public interface BookRepository extends Repository<Book, String> {

    void save(Book book);

    void delete(Book book);

    List<Book> findAll();

    Book findByIsbn(String isbn);

    List<Book> findByAuthorLastName(String lastName);

    List<Book> findLikeTitle(String title);

    List<Book> findByGenre(String genre);

    List<Book> findByPublishDate(LocalDate publishDate);

    int getBookStarRating(Book book);

    List<Genre> getBookGenres(Book book);

    List<Review> getBookReviews(Book book);

    boolean checkBookAvailable(Book book);

    List<Book> findByAuthorId(int authorId);

    void bookIsGenres(Book book, List<Genre> genres);

    List<Book> getUserBookRecommendations(User user);

    List<Genre> getGenreByBook(Book book);

    Author getAuthorByBook(Book book);
}
