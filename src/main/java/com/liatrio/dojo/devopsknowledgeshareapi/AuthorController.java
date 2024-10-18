package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @GetMapping
    public List<String> getAuthors() {
        // Return a sample list of authors
        return Arrays.asList("Author1", "Author2", "Author3");
    }
}
