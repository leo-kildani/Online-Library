package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subgenres")
@NoArgsConstructor
@AllArgsConstructor
public class Subgenre {

    @Embeddable
    public @Data static class SubgenreCompositeKey implements Serializable {

        private String genreName;

        private String subgenreName;

        public SubgenreCompositeKey(String genreName, String subgenreName) {
            this.genreName = genreName;
            this.subgenreName = subgenreName;
        }
    }

    @EmbeddedId
    @Getter
    private SubgenreCompositeKey subgenreId;
}
