package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.controller.CatalogController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookCheckout;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookCheckoutRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;

@Service
public class LibraryManagementSystemServiceImpl implements LibraryManagementSystemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookCheckoutRepository bookCheckoutRepository;

    @Autowired
    private GenreRepository genreRepository;

    private static final Logger logger = LoggerFactory.getLogger(LibraryManagementSystemServiceImpl.class);

    @Override
    public void addUser(String username, String password, String email, String firstName, String lastName,
            LocalDate dob, String role) {
        User newUser = new User(username, password, email, firstName, lastName, true, dob);
        userRepository.save(newUser);
        UserRole userRole = new UserRole(username, role);
        userRoleRepository.save(userRole);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
        userRoleRepository.findByUsername(user.getUsername()).forEach(u -> userRoleRepository.delete(u));
    }

    @Override
    public void addAuthor(String firstName, String lastName, String biography) {
        Author newAuthor = new Author(firstName, lastName, biography);
        authorRepository.save(newAuthor);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
        bookRepository.findByAuthorId(author.getAuthorId()).forEach(b -> bookRepository.delete(b));
    }

    @Override
    public void addBook(String isbn, String title, Author author, LocalDate publishDate, String description,
            List<Genre> genres) {
        Book book = new Book(isbn, title, author.getAuthorId(), publishDate, description);
        bookRepository.save(book);
        bookRepository.bookIsGenres(book, genres);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void addBookCheckout(User user, Book book) {
        BookCheckout bookCheckout = new BookCheckout(user.getUsername(), book.getIsbn());
        bookCheckoutRepository.save(bookCheckout);
    }

    @Override
    public void deleteBookCheckout(BookCheckout bookCheckout) {
        bookCheckoutRepository.delete(bookCheckout);
    }

    @Override
    public void addReview(String content, User user, Book book) {
        Review review = new Review(content, user.getUsername(), book.getIsbn());
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersBySimilarUsername(String username) {
        return userRepository.findLikeUsername(username);
    }

    @Override
    public List<User> getUsersBySimilarName(String name) {
        return userRepository.findLikeName(name);
    }

    @Override
    public Book getBook(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getBooksBySimilarTitle(String title) {
        return bookRepository.findLikeTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthorLastName(String authorLastName) {
        return bookRepository.findByAuthorLastName(authorLastName);
    }

    @Override
    public List<Book> getBooksByGenre(Genre genre) {
        return bookRepository.findByGenre(genre.getName());
    }

    @Override
    public List<Book> getBooksByPublishDate(LocalDate date) {
        return bookRepository.findByPublishDate(date);
    }

    @Override
    public List<Book> getBookByAppliedFilters(String title, String authorLastName, String genre, LocalDate publishDate) {
        List<Book> result = null;

        if (title != null) {
            List<Book> titleResults = bookRepository.findLikeTitle(title);
            result = (result == null) ? titleResults : intersect(result, titleResults);
        }
        if (authorLastName != null) {
            List<Book> authorResults = bookRepository.findByAuthorLastName(authorLastName);
            logger.info("books inside authorLastName if: " + authorResults);
            result = (result == null) ? authorResults : intersect(result, authorResults);
        }

        logger.info("result after name: " + result);
        if (genre != null) {
            List<Book> genreResults = bookRepository.findByGenre(genre);
            result = (result == null) ? genreResults : intersect(result, genreResults);
        }

        logger.info("result after genre: " + result);

        if (publishDate != null) {
            List<Book> dateResults = bookRepository.findByPublishDate(publishDate);
            result = (result == null) ? dateResults : intersect(result, dateResults);
        }

        logger.info("result after publish: " + result);

        return (result != null) ? result : new ArrayList<>();
    }

    // Helper method to intersect two lists
    private List<Book> intersect(List<Book> list1, List<Book> list2) {
        List<Book> result = new ArrayList<>(list1);
        result.retainAll(list2);
        return result;
    }

    @Override
    public int getBookStarRating(Book book) {
        return bookRepository.getBookStarRating(book);
    }

    @Override
    public boolean checkBookAvailable(Book book) {
        return bookRepository.checkBookAvailable(book);
    }

    @Override
    public List<Review> getUserReviews(User user) {
        return userRepository.getUserReviews(user);
    }

    @Override
    public List<Review> getBookReviews(Book book) {
        return bookRepository.getBookReviews(book);
    }

    @Override
    public double getUserLateFees(User user) {
        return userRepository.getUserLateFees(user);
    }

    @Override
    public void addGenre(String genreName, String overview) {
        Genre genre = new Genre(genreName, overview);
        genreRepository.save(genre);
    }

    @Override
    public List<Author> getUserFavoriteAuthor(User user) {
        return userRepository.getUserFavoriteAuthors(user);
    }

    @Override
    public void userFavoritesAuthor(User user, Author author) {
        userRepository.userFavoritesAuthor(user, author);
    }

    @Override
    public void userUnfavoritesAuthor(User user, Author author) {
        userRepository.userUnfavoritesAuthor(user, author);
    }

    @Override
    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Genre> getUserLikedGenres(User user) {
        return userRepository.getUserLikedGenres(user);
    }

    @Override
    public void userLikesGenre(User user, Genre genre) {
        userRepository.userLikesGenre(user, genre);
    }

    @Override
    public void userUnlikesGenre(User user, Genre genre) {
        userRepository.userUnlikesGenre(user, genre);
    }

    @Override
    public void deleteGenre(Genre genre) {
        genreRepository.delete(genre);
    }

    @Override
    public List<Book> getUserRecommendations(User user) {
        return bookRepository.getUserBookRecommendations(user);
    }

    @Override
    public List<Book> getUserCheckedBooks(User user) {
        return userRepository.getUserCheckedBooks(user);
    }
}
