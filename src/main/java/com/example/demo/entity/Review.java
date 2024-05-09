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
@Table(name = "reviews")
@NoArgsConstructor
public @Data class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "content")
    private String content;

    @Column(name = "username")
    private String username;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @Column(name = "isbn")
    private String isbn;

    public Review(String content, String posterUsername, String isbn) {
        this.content = content;
        this.username = posterUsername;
        this.publishDate = LocalDate.now();
        this.isbn = isbn;
    }

}
