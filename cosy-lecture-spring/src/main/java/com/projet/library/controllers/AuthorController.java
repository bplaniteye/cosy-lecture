package com.projet.library.controllers;

import com.projet.library.entities.AuthorEntity;
import com.projet.library.repositories.AuthorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author/")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("getAllAuthors")
    public ResponseEntity<Collection<AuthorEntity>> getAllAuthors() {
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }
}
