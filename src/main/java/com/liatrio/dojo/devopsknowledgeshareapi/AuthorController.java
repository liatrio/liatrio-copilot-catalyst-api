package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Arrays;

@RestController
public class AuthorController {

    @GetMapping("/authors")
    public List<String> getAuthors() {
        // This is a simple example. Replace with actual logic to fetch authors.
        return Arrays.asList("Author1", "Author2", "Author3");
    }
}
