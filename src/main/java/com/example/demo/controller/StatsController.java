package com.example.demo.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @GetMapping
    public Map<String, Object> getStats() {
        return Map.of(
                "message", "placeholder: user stats",
                "totals", Map.of(
                        "mealsLogged", 0,
                        "recipesSaved", 0,
                        "favorites", 0
                )
        );
    }
}