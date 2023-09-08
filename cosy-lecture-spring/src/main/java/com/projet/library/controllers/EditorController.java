package com.projet.library.controllers;

import com.projet.library.entities.EditorEntity;
import com.projet.library.repositories.EditorRepository;
//import com.projet.library.services.EditorService;
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
@RequestMapping("/api/editor/")
public class EditorController {
    private final EditorRepository editorRepository;

    @GetMapping("getAllEditors")
    public ResponseEntity <Collection<EditorEntity>> getAllEditors() {
        return new ResponseEntity<>(editorRepository.findAll() , HttpStatus.OK);
    }


}
