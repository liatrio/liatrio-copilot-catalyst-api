package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class InfoController {

    @GetMapping("/info")
    public String getInfo() {
        log.info("Received a GET request for /info");
        return "Welcome to the API version 1.0";
    }
}