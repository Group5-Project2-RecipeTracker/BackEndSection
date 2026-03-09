package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String listRecipes() {
        return """
            <h2>Recipes (placeholder)</h2>
            <ul>
              <li>1 - Grilled Chicken Bowl</li>
              <li>2 - Veggie Stir Fry</li>
            </ul>
            <p>Try: <a href="/api/recipes/1">/api/recipes/1</a></p>
        """;
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getRecipe(@PathVariable int id) {
        return """
            <h2>Recipe %d</h2>
            <p>Name: Sample Recipe</p>
            <p>Ingredients: Ingredient A, Ingredient B</p>
        """.formatted(id);
    }

    @PostMapping
    public Map<String, Object> createRecipe(@RequestBody Map<String, Object> body) {
        return Map.of("message", "placeholder: create recipe", "received", body);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateRecipe(@PathVariable int id, @RequestBody Map<String, Object> body) {
        return Map.of("message", "placeholder: update recipe", "id", id, "received", body);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteRecipe(@PathVariable int id) {
        return Map.of("message", "placeholder: delete recipe", "id", id);
    }
}