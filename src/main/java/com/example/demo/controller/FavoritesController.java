package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String listFavorites() {
        return """
            <h2>Favorites (placeholder)</h2>
            <ul>
              <li>1 - Chicken Bowl</li>
              <li>2 - Oatmeal</li>
            </ul>
        """;
    }

    @PostMapping("/{id}")
    public Map<String, Object> addFavorite(@PathVariable int id) {
        return Map.of("message", "placeholder: add favorite", "id", id);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> removeFavorite(@PathVariable int id) {
        return Map.of("message", "placeholder: remove favorite", "id", id);
    }
}