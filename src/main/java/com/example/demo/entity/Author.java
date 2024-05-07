package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "authors")
@NoArgsConstructor
public @Data class Author {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "author_id")
   private int authorId;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "biography")
   private String biography;

   public Author(String firstName, String lastName, String biography) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.biography = biography;
   }
}