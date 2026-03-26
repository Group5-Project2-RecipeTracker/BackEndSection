package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @GetMapping
    public Map<String, Object> getStats(
            @RequestParam(required = false) String userId
    ) {
        return Map.of(
                "message", "Return app or user stats from Firestore/aggregations",
                "userId", userId == null ? "" : userId,
                "routes", List.of(
                        "GET /api/stats",
                        "GET /api/stats/summary",
                        "GET /api/stats/user/{userId}"
                )
        );
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        return Map.of(
                "message", "Get overall summary stats",
                "sources", List.of("foods", "recipes", "mealPlans", "favorites")
        );
    }

    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserStats(@PathVariable String userId) {
        return Map.of(
                "message", "Get stats for one user",
                "userId", userId
        );
    }
}