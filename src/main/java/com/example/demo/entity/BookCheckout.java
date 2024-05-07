package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_checkouts")
@NoArgsConstructor
public @Data class BookCheckout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "checkout_id")
    private int checkoutId;

    @Column(name = "username")
    private String username;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "checkout_date")
    private LocalDate checkoutDate;

    private LocalDate dueDate;

    public BookCheckout(String username, String isbn) {
        this.username = username;
        this.isbn = isbn;
        this.checkoutDate = LocalDate.now();
        this.dueDate = checkoutDate.plusDays(30);
    }
}
