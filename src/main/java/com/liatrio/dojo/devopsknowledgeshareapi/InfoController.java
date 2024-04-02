package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok("Welcome to the API version 1.0");
    }
}
