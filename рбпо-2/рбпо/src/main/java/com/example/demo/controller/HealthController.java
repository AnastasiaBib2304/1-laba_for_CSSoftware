package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String health() {
        return "Application is running!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint is working!";
    }
}