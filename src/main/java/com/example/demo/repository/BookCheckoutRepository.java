package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BookCheckout;

@Repository
public interface BookCheckoutRepository extends JpaRepository<BookCheckout, Integer> {
    
}
