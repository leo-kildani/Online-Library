package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.entity.Author;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void save(Book book) {
        if (entityManager.find(Book.class, book.getIsbn()) != null) {
            entityManager.merge(book);
        } else {
            entityManager.persist(book);
        }
    }

    @Override
    @Transactional
    public void delete(Book book) {
        entityManager.remove(book);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findAll() {
        String queryString = "SELECT * FROM books";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        return (List<Book>) query.getResultList();
    }

    @Override
    public Book findByIsbn(String isbn) {
        String queryString = "SELECT * FROM books B WHERE B.isbn = :isbn";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("isbn", isbn);
        List<Book> result = (List<Book>) query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findByAuthorLastName(String lastName) {
        String queryString = "SELECT B.* FROM books B INNER JOIN authors A ON B.author_id = A.author_id WHERE A.last_name = :lastName";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("lastName", lastName);
        return (List<Book>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findLikeTitle(String title) {
        String queryString = "SELECT * FROM books B WHERE B.title LIKE :title";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("title", "%" + title + "%");
        return (List<Book>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findByGenre(String genreName) {
        String queryString = "SELECT B.* FROM books B INNER JOIN book_is_genre BG ON B.isbn = BG.isbn WHERE BG.genre_name = :genreName";
        Query query = entityManager
                .createNativeQuery(queryString, Book.class)
                .setParameter("genreName", genreName);
        return (List<Book>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findByPublishDate(LocalDate publishDate) {
        String queryString = "SELECT * FROM books B WHERE B.publish_date = :publishDate";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("publishDate", publishDate);
        return (List<Book>) query.getResultList();
    }

    @Override
    public double getBookStarRating(Book book) {
        String queryString = "SELECT AVG(B.stars) FROM user_rates_book B WHERE B.isbn = :isbn";
        Query query = entityManager.createNativeQuery(queryString, Double.class);
        query.setParameter("isbn", book.getIsbn());
        List<Double> result = (List<Double>) query.getResultList();
        return result.isEmpty() ? 0 : result.get(0).doubleValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Genre> getBookGenres(Book book) {
        String queryString = "SELECT G.* FROM book_is_genre BG INNER JOIN genres G ON BG.genre_name = G.genre_name WHERE BG.isbn = :isbn";
        Query query = entityManager.createNativeQuery(queryString, Genre.class);
        query.setParameter("isbn", book.getIsbn());
        return (List<Genre>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Review> getBookReviews(Book book) {
        String queryString = "SELECT * FROM reviews R WHERE R.isbn = :isbn";
        Query query = entityManager.createNativeQuery(queryString, Review.class);
        query.setParameter("isbn", book.getIsbn());
        return (List<Review>) query.getResultList();
    }

    @Override
    public boolean checkBookAvailable(Book book) {
        String queryString = "SELECT COUNT(*) > 0 FROM book_checkouts WHERE isbn = :isbn";
        Query query = entityManager
                .createNativeQuery(queryString, Boolean.class)
                .setParameter("isbn", book.getIsbn());
        List<Boolean> result = (List<Boolean>) query.getResultList();
        return result.isEmpty() ? false : result.get(0).booleanValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findByAuthorId(int authorId) {
        String queryString = "SELECT * FROM books B WHERE B.author_id = :author_id";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("author_id", authorId);
        return (List<Book>) query.getResultList();
    }

    @Override
    public void bookIsGenres(Book book, List<Genre> genres) {
        String insertString = "INSERT INTO book_is_genre (isbn, genre_name) VALUES (?, ?)";
        for (Genre genre : genres) {
            jdbcTemplate.update(insertString, book.getIsbn(), genre.getName());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> getUserBookRecommendations(User user) {
        String queryString = " (SELECT b.* FROM books b JOIN authors a ON b.author_id = a.author_id JOIN user_favorites_author ufa ON a.author_id = ufa.author_id WHERE ufa.username = :username ORDER BY b.publish_date DESC LIMIT 5) UNION (SELECT b.* FROM books b JOIN book_is_genre big ON b.isbn = big.isbn JOIN user_likes_genres ulg ON big.genre_name = ulg.genre_name WHERE ulg.username = :username ORDER BY RAND() LIMIT 5);";
        Query query = entityManager
            .createNativeQuery(queryString, Book.class)
            .setParameter("username", user.getUsername());
        return (List<Book>) query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Genre> getGenreByBook(Book book) {
        String queryString = "SELECT G.* FROM genres G " +
                "JOIN book_is_genre BG ON G.genre_name = BG.genre_name " +
                "WHERE BG.isbn = :isbn";
        Query query = entityManager.createNativeQuery(queryString, Genre.class);
        query.setParameter("isbn", book.getIsbn());
        return (List<Genre>) query.getResultList();
    }

    @Override
    public Author getAuthorByBook(Book book) {
        String queryString = "SELECT A.* FROM authors A " +
                "JOIN books B ON A.author_id = B.author_id " +
                "WHERE B.isbn = :isbn";
        Query query = entityManager.createNativeQuery(queryString, Author.class);
        query.setParameter("isbn", book.getIsbn());
        List<Author> result = (List<Author>) query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

}