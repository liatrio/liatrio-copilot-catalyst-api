package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Arrays;

@RestController
public class AuthorController {

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        Author author1 = new Author(1L, "Author One");
        Author author2 = new Author(2L, "Author Two");
        return Arrays.asList(author1, author2);
    }
}