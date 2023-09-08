package com.projet.library.dao.request;

import java.sql.Date;
//import java.time.LocalDateTime;

import com.projet.library.entities.AuthorEntity;
import com.projet.library.entities.CategoryEntity;
import com.projet.library.entities.EditorEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateBookRequest {
    private String title;
    private Integer quantity;
    private String summary;
    private Boolean available;
    private String  createdAt;
    private Date publicationYear;
    private String version;
    private AuthorEntity author;
    private CategoryEntity category;
    private EditorEntity editor;
}
