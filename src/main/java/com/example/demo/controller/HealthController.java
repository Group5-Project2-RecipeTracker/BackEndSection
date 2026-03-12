package com.example.demo.controller;

import java.time.Instant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping(value = "/health", produces = MediaType.TEXT_HTML_VALUE)
    public String health() {
        return "<h2>Status: OK</h2><p>timestamp: " + Instant.now() + "</p>";
    }
}