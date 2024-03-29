package com.liatrio.dojo.devopsknowledgeshareapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VersionController {


    @GetMapping(value = "/version", produces = "application/json")
    public Map<String, String> version() {
        Map<String, String> map = new HashMap<>();
        String deploymentType = System.getenv("DEPLOYMENT_TYPE") != null ? System.getenv("DEPLOYMENT_TYPE") : "blue";
        map.put("version", deploymentType);
        return map;
    }

}
