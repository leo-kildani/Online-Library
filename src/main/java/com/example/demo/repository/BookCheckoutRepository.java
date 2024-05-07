package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BookCheckout;
import java.util.List;


@Repository
public interface BookCheckoutRepository extends JpaRepository<BookCheckout, Integer> {
   
    @Query(value = "SELECT * FROM book_checkouts WHERE username = :username", nativeQuery = true)
    List<BookCheckout> findByUsername(String username);
}
