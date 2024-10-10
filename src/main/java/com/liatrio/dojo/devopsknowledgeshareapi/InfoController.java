package com.liatrio.dojo.devopsknowledgeshareapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {

    @GetMapping(value = "/info", produces = "application/json")
    public Map<String, String> getInfo() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the API version 1.0");
        return response;
    }
}