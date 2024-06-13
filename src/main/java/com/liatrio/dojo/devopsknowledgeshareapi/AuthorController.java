package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class AuthorController {

    @GetMapping("/authors")
    public ResponseEntity<List<String>> getAuthors() {
        // Mocked list of author names, replace with your actual data model if needed
        List<String> authors = Arrays.asList("Author One", "Author Two", "Author Three");
        return ResponseEntity.ok(authors); // Return the list with an OK status
    }
}