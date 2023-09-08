package com.projet.library.controllers;

import java.util.Collection;
import java.util.List;
//import com.projet.library.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projet.library.entities.BookEntity;
import com.projet.library.repositories.BookRepository;

import lombok.RequiredArgsConstructor;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

	private final BookRepository bookRepository;

	// Method to get all books
	@GetMapping("/getAllBooks")
	public ResponseEntity<Collection<BookEntity>> getAllBooks() {
		return new ResponseEntity<>(bookRepository.findAll(), HttpStatus.OK);
	}

	// Method to get informations about a book
	@GetMapping("getBookById/{id}")
	public ResponseEntity<BookEntity> getBookById(@PathVariable Integer id) {
		BookEntity book = bookRepository.findBook(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	// Method to get books of a category
	@GetMapping("getBooksByCategory/{id}")
	public ResponseEntity<Collection<BookEntity>> getBooksByCategory(@PathVariable Integer id) {
		Collection<BookEntity> books = bookRepository.findByCategoryId(id);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	// Method to get books of an author
	@GetMapping("getBooksByAuthor/{id}")
	public ResponseEntity<Collection<BookEntity>> getBooksbyAuthor(@PathVariable Integer id) {
		Collection<BookEntity> books = bookRepository.findByAuthorId(id);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	// Method to get books of an editor
	@GetMapping("getBooksByEditor/{id}")
	public ResponseEntity<Collection<BookEntity>> getBooksbyEditor(@PathVariable Integer id) {
		Collection<BookEntity> books = bookRepository.findByEditorId(id);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("latestBooks")
	public ResponseEntity<List<BookEntity>> getLatestBooks() {
		List<BookEntity> latestBooks = bookRepository.findTop5ByOrderByPublicationYearDesc();
		return new ResponseEntity<>(latestBooks, HttpStatus.OK);
	}

	// Method to search a book
	// @GetMapping("search")
	// public List<BookEntity> searchBook(@RequestParam String text) {
	// 	return this.bookService.searchBook(text);
	// }

	// Method to get available books
	@GetMapping("getAvailableBooks")
	public ResponseEntity<Collection<BookEntity>> getAvailableBooks() {
		return new ResponseEntity<>(bookRepository.findAvailableBooks(), HttpStatus.OK);
	}
}
