package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String getMealPlan() {
        return """
            <h2>Meal Plan (placeholder)</h2>
            <ul>
              <li>Monday: Breakfast, Lunch</li>
              <li>Tuesday: Dinner</li>
            </ul>
        """;
    }

    @PostMapping("/entries")
    public Map<String, Object> addMealEntry(@RequestBody Map<String, Object> body) {
        return Map.of("message", "placeholder: add meal entry", "received", body);
    }

    @DeleteMapping("/entries/{id}")
    public Map<String, Object> deleteMealEntry(@PathVariable int id) {
        return Map.of("message", "placeholder: delete meal entry", "id", id);
    }
}