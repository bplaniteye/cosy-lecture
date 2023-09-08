package com.projet.library.services;

import com.projet.library.entities.EditorEntity;
import com.projet.library.repositories.EditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class EditorService {

        private final EditorRepository editorRepository;

        @Autowired
        public EditorService(EditorRepository editorRepository) {
            this.editorRepository = editorRepository;
        }

    public void updateEditor(@PathVariable int id) {
        String newName = "<NAME>";
        Optional<EditorEntity> editorToUpdate = editorRepository.findById(id);
        if(editorToUpdate.isPresent()) {
            EditorEntity editor = editorToUpdate.get();
            editor.setName(newName);
            editorRepository.save(editor);
        }
    }

}
