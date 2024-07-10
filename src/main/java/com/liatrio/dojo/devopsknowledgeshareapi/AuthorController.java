package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }
}