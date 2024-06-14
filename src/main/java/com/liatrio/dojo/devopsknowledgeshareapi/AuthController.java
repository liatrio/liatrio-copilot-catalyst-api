package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/authors")
    public ResponseEntity<String> getAuthors() {
        return ResponseEntity.ok("Authors endpoint");
    }
}
