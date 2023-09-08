package com.projet.library.controllers;

import java.net.URI;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.projet.library.dao.request.CreateBookRequest;
import com.projet.library.dao.request.CreateBorrowRequest;
import com.projet.library.entities.*;
import com.projet.library.repositories.*;
import com.projet.library.services.BookService;

import com.projet.library.services.BorrowService;
import com.projet.library.services.EditorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final BorrowService borrowService;
    public final BorrowRepository borrowRepository;
    private final CategoryRepository categoryRepository;
    private final EditorRepository editorRepository;
    private final EditorService editorService;

    @PostMapping("/createBook")
    public ResponseEntity<BookEntity> createBook(@RequestBody CreateBookRequest request) throws ServerException {
        BookEntity book = bookService.createBook(request);
        if (book == null) {
            throw new ServerException("Impossible de créer le livre");
        } else {
            URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
    }

    @PostMapping("/admin-create-borrow")
    public ResponseEntity<BorrowEntity> adminCreateBorrow(@RequestBody CreateBorrowRequest request) throws ServerException {
        BorrowEntity borrow = borrowService.adminCreateBorrow(request);
        if(borrow==null) {
            throw new ServerException("Impossible de réaliser l'emprunt");
        } else {
            URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(borrow.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
    }

    @PostMapping("/createCategory")
	public ResponseEntity<CategoryEntity>createCategory(@RequestBody CategoryEntity newCategory) throws ServerException {
        CategoryEntity category = categoryRepository.save(newCategory);
        if(category==null) {
            throw new ServerException("Impossible de créer la categorie");
        } else {
            URI url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
	}

    @PostMapping("/createAuthor")
    public ResponseEntity<AuthorEntity>createAuthor(@RequestBody AuthorEntity newAuthor) throws ServerException {
        AuthorEntity author = authorRepository.save(newAuthor);
        if(author==null) {
            throw new ServerException("Impossible de créer un auteur");
        } else {
            URI url =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(author.getId()).toUri();
            return ResponseEntity.created(url).build();
        }        
        
    }

    @PostMapping("/createEditor")
    public ResponseEntity<EditorEntity>createEditor(@RequestBody EditorEntity newEditor) throws ServerException {
        EditorEntity editor = editorRepository.save(newEditor);
        if(editor==null) {
            throw new ServerException("Impossible de créer un éditeur");
        } else {
            URI url =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(editor.getId()).toUri();
            return ResponseEntity.created(url).build();
        } 
    }

    @PutMapping("/update-editor/{id}")
    public ResponseEntity<EditorEntity> updateEditor(@PathVariable Integer id, @RequestBody EditorEntity editorData) throws ServerException {
      Optional <EditorEntity> editor = editorRepository.findById(id);
     if (editor.isPresent()) {
         EditorEntity editorToUpdate = editor.get();
         editorToUpdate.setName(editorData.getName());
         final EditorEntity updatedEditor = editorRepository.save(editorToUpdate);
         return ResponseEntity.ok(updatedEditor);
     } else {
         throw new ServerException("Impossible de mettre à jour l'éditeur");
     }
    }

    @DeleteMapping("delete-editor/{id}")
    public ResponseEntity<EditorEntity> deleteEditor(@PathVariable Integer id) {
        Optional <EditorEntity> editor = editorRepository.findById(id);
               if (editor.isPresent()) {
                   EditorEntity editorToDelete = editor.get();
                   editorRepository.delete(editorToDelete);
                   return ResponseEntity.ok(editorToDelete);
               }
                return ResponseEntity.notFound().build();
    }
       /* personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<AuthorEntity> updateAuthor(@PathVariable Integer id, @RequestBody AuthorEntity author) throws ServerException {
        AuthorEntity updatedAuthor = authorRepository.save(author);
        if(updatedAuthor==null) {
            throw new ServerException("Impossible de mettre à jour l'auteur");
        } else {
            URI url =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedAuthor.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<BookEntity> deleteBook(@PathVariable Integer id) throws ServerException {
        BookEntity book = bookService.deleteBook(id);
        if(book==null) {
            throw new ServerException("Impossible de supprimer le livre");
        } else {
            URI url =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable Integer id, @RequestBody BookEntity book) throws ServerException {
        BookEntity updatedBook = bookService.updateBook(id, book);
        if(updatedBook==null) {
            throw new ServerException("Impossible de mettre à jour le livre");
        } else {
            URI url =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedBook.getId()).toUri();
            return ResponseEntity.created(url).build();
        }
    }

    */

}
