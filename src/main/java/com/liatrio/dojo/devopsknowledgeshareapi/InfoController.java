package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/info")
    public String getInfo() {
        return "Welcome to the API version 1.0";
    }
}