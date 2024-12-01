package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.liatrio.dojo.devopsknowledgeshareapi.AuthorService;


@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/authors")
    public Object getAuthors() {
        // TODO: Replace Object with your Author class
        return authorService.getAuthors();
    }
}