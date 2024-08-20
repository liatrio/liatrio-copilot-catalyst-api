package com.liatrio.dojo.devopsknowledgeshareapi;

import com.liatrio.dojo.devopsknowledgeshareapi.model.Author;
import com.liatrio.dojo.devopsknowledgeshareapi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
}