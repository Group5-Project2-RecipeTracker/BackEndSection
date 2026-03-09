package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {

    // Example: /api/meal-plans?week=2026-03-09
    @GetMapping
    public Map<String, Object> getMealPlan(@RequestParam(required = false) String week) {
        return Map.of(
                "message", "placeholder: get meal plan for week",
                "week", week,
                "days", List.of()
        );
    }

    // Example: /api/meal-plans/entries (add breakfast/lunch/dinner entry)
    @PostMapping("/entries")
    public Map<String, Object> addMealEntry(@RequestBody(required = false) Map<String, Object> body) {
        return Map.of(
                "message", "placeholder: add meal entry",
                "received", body
        );
    }

    @PutMapping("/entries/{entryId}")
    public Map<String, Object> replaceMealEntry(@PathVariable String entryId,
                                                @RequestBody(required = false) Map<String, Object> body) {
        return Map.of(
                "message", "placeholder: replace meal entry",
                "entryId", entryId,
                "received", body
        );
    }

    @DeleteMapping("/entries/{entryId}")
    public Map<String, Object> deleteMealEntry(@PathVariable String entryId) {
        return Map.of(
                "message", "placeholder: delete meal entry",
                "entryId", entryId
        );
    }
}