//package com.example.demo.repository;
//
//import com.example.demo.entity.Genre;
//import java.util.List;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class GenreRepositoryImpl implements GenreRepository{
//    @Override
//    public List<Genre> getAllGenres() {
//        String queryString = "SELECT * FROM genres";
//        Query query = entityManager.createNativeQuery(queryString, Genre.class);
//        return (List<Genre>) query.getResultList();
//    }
//}
