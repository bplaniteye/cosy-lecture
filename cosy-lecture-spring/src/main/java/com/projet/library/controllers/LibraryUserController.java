package com.projet.library.controllers;

import com.projet.library.entities.LibraryUserEntity;
import com.projet.library.exception.UserNotFoundException;
import com.projet.library.repositories.LibraryUserRepository;
import com.projet.library.services.LibraryUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/libraryUser/")
public class LibraryUserController {

    //@Autowired
    private final LibraryUserRepository libraryUserRepository;
    private final LibraryUserService libraryUserService;

    @GetMapping("getAllLibraryUsers")
    public ResponseEntity<Collection<LibraryUserEntity>> getAllLibraryUsers() {
        Sort sort = Sort.by(Sort.Order.asc("lastName"));
        return new ResponseEntity<>(libraryUserRepository.findAll(sort), HttpStatus.OK);
    }

    // Endpoint pour obtenir le prénom de l'utilisateur par son ID
    @GetMapping("profile/{id}")
    public ResponseEntity<LibraryUserEntity> getUserById(@PathVariable Integer id) {
        try {
            LibraryUserEntity user = libraryUserService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}