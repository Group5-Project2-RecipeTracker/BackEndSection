package com.example.demo.controller;

import com.example.demo.model.Recipe;
import com.example.demo.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> listRecipes() throws Exception {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable String id) throws Exception {
        Recipe recipe = recipeService.getRecipeById(id);

        if (recipe == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Recipe not found", "id", id));
        }

        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) throws Exception {
        return ResponseEntity.ok(recipeService.createRecipe(recipe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable String id, @RequestBody Recipe recipe) throws Exception {
        Recipe updated = recipeService.updateRecipe(id, recipe);

        if (updated == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Recipe not found", "id", id));
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteRecipe(@PathVariable String id) throws Exception {
        boolean deleted = recipeService.deleteRecipe(id);

        if (!deleted) {
            return ResponseEntity.status(404).body(Map.of("message", "Recipe not found", "id", id));
        }

        return ResponseEntity.ok(Map.of("message", "Recipe deleted", "id", id));
    }
}