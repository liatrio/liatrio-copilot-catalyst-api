package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    // Add more methods as needed
}