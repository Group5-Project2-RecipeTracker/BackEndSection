package com.example.demo.controller;

import java.time.Instant;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "OK",
                "timestamp", Instant.now().toString()
        );
    }
}