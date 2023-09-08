package com.projet.library.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projet.library.dao.request.CreateBookRequest;
import com.projet.library.entities.BookEntity;
import com.projet.library.exception.BookNotFoundException;
import com.projet.library.repositories.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookEntity createBook(CreateBookRequest request) {
        // Créer une nouvelle instance de BookEntity à partir des données du DAO (CreateBookRequest)
        BookEntity newBook = new BookEntity();
        newBook.setTitle(request.getTitle());
        newBook.setQuantity(request.getQuantity());
        newBook.setSummary(request.getSummary());
        newBook.setAvailable(true);
        newBook.setCreatedAt(request.getCreatedAt());
        newBook.setEditor(request.getEditor());
        newBook.setPublicationYear(request.getPublicationYear());
        newBook.setVersion(request.getVersion());
        newBook.setAuthor(request.getAuthor());
        newBook.setCategory(request.getCategory());

        return bookRepository.save(newBook);
    }

    public BookEntity getBookById(Integer id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BookNotFoundException("Book with ID " + id + " not found");
        }
    }
}
