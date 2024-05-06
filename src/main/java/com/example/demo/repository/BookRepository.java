package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class BookRepository implements BookRepositoryI {

    private EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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
        return (Book) query.getSingleResult();
    }

    @Override
    public List<Book> findByAuthorLastName(String lastName) {
        String queryString = "SELECT B.* FROM books B INNER JOIN authors A ON B.author_id = A.author_id WHERE A.last_name = :lastName";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("lastName", lastName);
        return (List<Book>) query.getResultList();
    }

    @Override
    public List<Book> findLikeTitle(String title) {
        String queryString = "SELECT * FROM books B WHERE B.title LIKE :title";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("tite", "%" + title + "%");
        return (List<Book>) query.getResultList();
    }

    @Override
    public List<Book> findByGenre(String genre) {
        String queryString = "SELECT * FROM books B WHERE B.genre_name = :genre";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("genre", genre);
        return (List<Book>) query.getResultList();
    }

    @Override
    public List<Book> findByPublishDate(LocalDate publishDate) {
        String queryString = "SELECT * FROM books B WHERE B.publish_date = :publishDate";
        Query query = entityManager.createNativeQuery(queryString, Book.class);
        query.setParameter("publishDate", publishDate);
        return (List<Book>) query.getResultList();
    }

}
