package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @GetMapping
    public Map<String, Object> listFoods() {
        return Map.of(
                "message", "placeholder: list foods",
                "items", List.of()
        );
    }

    @GetMapping("/{foodId}")
    public Map<String, Object> getFood(@PathVariable String foodId) {
        return Map.of(
                "message", "placeholder: get food",
                "foodId", foodId
        );
    }

    @PostMapping
    public Map<String, Object> createFood(@RequestBody(required = false) Map<String, Object> body) {
        return Map.of(
                "message", "placeholder: create food",
                "received", body
        );
    }

    @PutMapping("/{foodId}")
    public Map<String, Object> updateFood(@PathVariable String foodId,
                                          @RequestBody(required = false) Map<String, Object> body) {
        return Map.of(
                "message", "placeholder: update food",
                "foodId", foodId,
                "received", body
        );
    }

    @DeleteMapping("/{foodId}")
    public Map<String, Object> deleteFood(@PathVariable String foodId) {
        return Map.of(
                "message", "placeholder: delete food",
                "foodId", foodId
        );
    }
}