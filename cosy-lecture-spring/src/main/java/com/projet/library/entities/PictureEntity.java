package com.projet.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "picture")
public class PictureEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, length =50)
    private String name;

    @Column (nullable = false, length =50)
    private String url;

    @Column (nullable = false, length =255)
    private String description;

    //Relationships
    @OneToOne
    private LibraryUserEntity user;

    @ManyToOne
    private AuthorEntity author;

    @ManyToOne
    private BookEntity book;
}
