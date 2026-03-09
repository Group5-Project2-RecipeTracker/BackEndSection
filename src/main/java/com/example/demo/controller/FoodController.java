package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String listFoods() {
        return """
            <h2>Foods (placeholder)</h2>
            <ul>
              <li>1 - Chicken Breast (200 cal)</li>
              <li>2 - Brown Rice (215 cal)</li>
            </ul>
            <p>Try: <a href="/api/foods/1">/api/foods/1</a></p>
        """;
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getFood(@PathVariable int id) {
        return "<h2>Food " + id + "</h2><p>(placeholder)</p>";
    }

    @PostMapping
    public Map<String, Object> createFood(@RequestBody Map<String, Object> body) {
        return Map.of("message", "placeholder: create food", "received", body);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateFood(@PathVariable int id, @RequestBody Map<String, Object> body) {
        return Map.of("message", "placeholder: update food", "id", id, "received", body);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteFood(@PathVariable int id) {
        return Map.of("message", "placeholder: delete food", "id", id);
    }
}