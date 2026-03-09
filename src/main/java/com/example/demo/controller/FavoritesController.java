package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    @GetMapping
    public Map<String, Object> listFavorites() {
        return Map.of(
                "message", "placeholder: list favorites",
                "items", List.of()
        );
    }

    @PostMapping("/{foodId}")
    public Map<String, Object> addFavorite(@PathVariable String foodId) {
        return Map.of(
                "message", "placeholder: add favorite",
                "foodId", foodId
        );
    }

    @DeleteMapping("/{foodId}")
    public Map<String, Object> removeFavorite(@PathVariable String foodId) {
        return Map.of(
                "message", "placeholder: remove favorite",
                "foodId", foodId
        );
    }
}