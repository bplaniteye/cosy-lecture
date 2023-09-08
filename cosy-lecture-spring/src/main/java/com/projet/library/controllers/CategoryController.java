package com.projet.library.controllers;

import com.projet.library.entities.CategoryEntity;
import com.projet.library.repositories.CategoryRepository;

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
@RequestMapping("/api/category/")
public  class CategoryController {
    private final CategoryRepository categoryRepository;

    @GetMapping("getAllCategories")
    public ResponseEntity<Collection<CategoryEntity>> getAllBooks() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }
}
