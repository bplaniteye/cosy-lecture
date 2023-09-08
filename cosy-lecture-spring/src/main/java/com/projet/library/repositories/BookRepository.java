package com.projet.library.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.library.entities.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    List<BookEntity> findByTitle(String title);

    Optional<BookEntity> findById(Integer id);

    @Query("SELECT b FROM BookEntity b ORDER BY b.publicationYear DESC")
    List<BookEntity> findTop5ByOrderByPublicationYearDesc();
    
    @Query("SELECT b FROM BookEntity b WHERE b.category.id = :categoryId")
    List<BookEntity> findByCategoryId(@Param("categoryId") Integer categoryId);

    @Query("SELECT b FROM BookEntity b WHERE b.author.id = :authorId")
    List<BookEntity> findByAuthorId(@Param("authorId") Integer authorId);

    @Query("SELECT b FROM BookEntity b WHERE b.editor.id = :editorId")
    List<BookEntity> findByEditorId(@Param("editorId") Integer editorId);

    @Query("SELECT b FROM BookEntity b WHERE b.id = :bookId")
    BookEntity findBook(@Param("bookId") Integer bookId);

    @Query("SELECT b FROM BookEntity b WHERE b.available = true ORDER BY b.title ASC ")
    List <BookEntity> findAvailableBooks();

}
