package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @GetMapping
    public Map<String, Object> listRecipes(@RequestParam(required = false) String q,
                                           @RequestParam(required = false) String tag) {
        return Map.of(
                "message", "placeholder: list recipes",
                "query", q,
                "tag", tag,
                "items", List.of()
        );
    }

    @GetMapping("/{recipeId}")
    public Map<String, Object> getRecipe(@PathVariable String recipeId) {
        return Map.of(
                "message", "placeholder: get recipe",
                "recipeId", recipeId
        );
    }

    @PostMapping
    public Map<String, Object> createRecipe(@RequestBody(required = false) Map<String, Object> body) {
        return Map.of(
                "message", "placeholder: create recipe",
                "received", body
        );
    }

    @PutMapping("/{recipeId}")
    public Map<String, Object> updateRecipe(@PathVariable String recipeId,
                                            @RequestBody(required = false) Map<String, Object> body) {
        return Map.of(
                "message", "placeholder: update recipe",
                "recipeId", recipeId,
                "received", body
        );
    }

    @DeleteMapping("/{recipeId}")
    public Map<String, Object> deleteRecipe(@PathVariable String recipeId) {
        return Map.of(
                "message", "placeholder: delete recipe",
                "recipeId", recipeId
        );
    }
}