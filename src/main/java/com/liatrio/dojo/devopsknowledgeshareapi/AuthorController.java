package com.liatrio.dojo.devopsknowledgeshareapi;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<?> getAllAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findAuthorById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}