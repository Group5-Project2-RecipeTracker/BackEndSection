package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    @GetMapping
    public Map<String, Object> listFavorites(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String type
    ) {
        return Map.of(
                "message", "List favorites from Firestore",
                "collection", "favorites",
                "filters", Map.of(
                        "userId", userId == null ? "" : userId,
                        "type", type == null ? "" : type
                ),
                "routes", List.of(
                        "GET /api/favorites",
                        "POST /api/favorites",
                        "DELETE /api/favorites/{favoriteId}"
                )
        );
    }

    @PostMapping
    public Map<String, Object> addFavorite(@RequestBody Map<String, Object> body) {
        return Map.of(
                "message", "Create favorite in Firestore",
                "collection", "favorites",
                "received", body,
                "expectedFields", List.of("userId", "itemId", "type")
        );
    }

    @DeleteMapping("/{favoriteId}")
    public Map<String, Object> removeFavorite(@PathVariable String favoriteId) {
        return Map.of(
                "message", "Delete favorite from Firestore",
                "favoriteId", favoriteId,
                "collection", "favorites"
        );
    }
}