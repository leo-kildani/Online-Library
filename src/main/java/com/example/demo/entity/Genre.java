package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genres")
@NoArgsConstructor
public @Data class Genre {
   
    @Id
    @Column(name = "genre_name")
    private String name;

    @Column(name = "overview")
    private String overview;
}
