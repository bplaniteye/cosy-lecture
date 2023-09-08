package com.projet.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String label;

    @Column(length = 200)
    private String definition;

    // Relationships
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Collection<BookEntity> bookCollection;

}
