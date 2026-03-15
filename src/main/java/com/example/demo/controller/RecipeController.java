package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private static final List<Map<String, Object>> RECIPES = new ArrayList<>(List.of(
            Map.of("id", 1,  "name", "Grilled Chicken Bowl",         "category", "High Protein", "calories", 520, "protein_g", 48, "carbs_g", 45, "fat_g", 10, "fiber_g", 5,  "servings", 1, "meal", "Lunch/Dinner"),
            Map.of("id", 2,  "name", "Veggie Stir Fry",              "category", "Vegetarian",   "calories", 310, "protein_g", 9,  "carbs_g", 42, "fat_g", 12, "fiber_g", 8,  "servings", 2, "meal", "Lunch/Dinner"),
            Map.of("id", 3,  "name", "Salmon & Quinoa",              "category", "High Protein", "calories", 580, "protein_g", 46, "carbs_g", 40, "fat_g", 22, "fiber_g", 6,  "servings", 1, "meal", "Dinner"),
            Map.of("id", 4,  "name", "Oatmeal Banana Bowl",          "category", "Vegetarian",   "calories", 340, "protein_g", 9,  "carbs_g", 65, "fat_g", 6,  "fiber_g", 8,  "servings", 1, "meal", "Breakfast"),
            Map.of("id", 5,  "name", "Turkey Taco Bowl",             "category", "High Protein", "calories", 490, "protein_g", 38, "carbs_g", 44, "fat_g", 14, "fiber_g", 7,  "servings", 2, "meal", "Lunch/Dinner"),
            Map.of("id", 6,  "name", "Avocado Egg Toast",            "category", "Balanced",     "calories", 380, "protein_g", 16, "carbs_g", 28, "fat_g", 24, "fiber_g", 7,  "servings", 1, "meal", "Breakfast"),
            Map.of("id", 7,  "name", "Black Bean Burrito",           "category", "Vegetarian",   "calories", 460, "protein_g", 18, "carbs_g", 70, "fat_g", 10, "fiber_g", 14, "servings", 2, "meal", "Lunch/Dinner"),
            Map.of("id", 8,  "name", "Greek Yogurt Parfait",         "category", "Low Fat",      "calories", 280, "protein_g", 20, "carbs_g", 38, "fat_g", 4,  "fiber_g", 4,  "servings", 1, "meal", "Breakfast/Snack"),
            Map.of("id", 9,  "name", "Shrimp Fried Rice",            "category", "High Protein", "calories", 540, "protein_g", 32, "carbs_g", 62, "fat_g", 14, "fiber_g", 3,  "servings", 2, "meal", "Lunch/Dinner"),
            Map.of("id", 10, "name", "Spinach Omelette",             "category", "Low Carb",     "calories", 260, "protein_g", 20, "carbs_g", 4,  "fat_g", 18, "fiber_g", 2,  "servings", 1, "meal", "Breakfast"),
            Map.of("id", 11, "name", "Beef & Broccoli",              "category", "High Protein", "calories", 480, "protein_g", 40, "carbs_g", 20, "fat_g", 24, "fiber_g", 4,  "servings", 2, "meal", "Dinner"),
            Map.of("id", 12, "name", "Whole Wheat Pasta Primavera",  "category", "Vegetarian",   "calories", 420, "protein_g", 14, "carbs_g", 72, "fat_g", 8,  "fiber_g", 10, "servings", 2, "meal", "Lunch/Dinner"),
            Map.of("id", 13, "name", "Tuna Salad Wrap",              "category", "Low Fat",      "calories", 340, "protein_g", 30, "carbs_g", 32, "fat_g", 8,  "fiber_g", 4,  "servings", 1, "meal", "Lunch"),
            Map.of("id", 14, "name", "Peanut Butter Banana Smoothie","category", "Balanced",     "calories", 400, "protein_g", 14, "carbs_g", 52, "fat_g", 16, "fiber_g", 5,  "servings", 1, "meal", "Breakfast/Snack"),
            Map.of("id", 15, "name", "Sweet Potato & Turkey Bake",   "category", "High Protein", "calories", 510, "protein_g", 36, "carbs_g", 48, "fat_g", 12, "fiber_g", 8,  "servings", 2, "meal", "Dinner"),
            Map.of("id", 16, "name", "Cottage Cheese Fruit Bowl",    "category", "Low Fat",      "calories", 220, "protein_g", 18, "carbs_g", 28, "fat_g", 3,  "fiber_g", 3,  "servings", 1, "meal", "Breakfast/Snack"),
            Map.of("id", 17, "name", "Kale Caesar Salad",            "category", "Vegetarian",   "calories", 290, "protein_g", 10, "carbs_g", 18, "fat_g", 20, "fiber_g", 4,  "servings", 1, "meal", "Lunch"),
            Map.of("id", 18, "name", "Chicken & Sweet Potato Curry", "category", "High Protein", "calories", 560, "protein_g", 42, "carbs_g", 52, "fat_g", 16, "fiber_g", 9,  "servings", 2, "meal", "Dinner"),
            Map.of("id", 19, "name", "Almond Butter Overnight Oats", "category", "Balanced",     "calories", 410, "protein_g", 12, "carbs_g", 54, "fat_g", 16, "fiber_g", 8,  "servings", 1, "meal", "Breakfast"),
            Map.of("id", 20, "name", "Sirloin Steak & Veggies",      "category", "High Protein", "calories", 620, "protein_g", 52, "carbs_g", 14, "fat_g", 36, "fiber_g", 5,  "servings", 1, "meal", "Dinner"),
            Map.of("id", 21, "name", "Quinoa Veggie Bowl",           "category", "Vegetarian",   "calories", 370, "protein_g", 14, "carbs_g", 58, "fat_g", 8,  "fiber_g", 10, "servings", 1, "meal", "Lunch/Dinner"),
            Map.of("id", 22, "name", "Blueberry Protein Smoothie",   "category", "Low Fat",      "calories", 290, "protein_g", 22, "carbs_g", 36, "fat_g", 4,  "fiber_g", 5,  "servings", 1, "meal", "Breakfast/Snack"),
            Map.of("id", 23, "name", "Baked Salmon & Asparagus",     "category", "Low Carb",     "calories", 440, "protein_g", 44, "carbs_g", 10, "fat_g", 24, "fiber_g", 4,  "servings", 1, "meal", "Dinner"),
            Map.of("id", 24, "name", "Chicken Caesar Wrap",          "category", "Balanced",     "calories", 480, "protein_g", 36, "carbs_g", 40, "fat_g", 18, "fiber_g", 3,  "servings", 1, "meal", "Lunch"),
            Map.of("id", 25, "name", "Lentil Soup",                  "category", "Vegetarian",   "calories", 320, "protein_g", 18, "carbs_g", 48, "fat_g", 4,  "fiber_g", 16, "servings", 2, "meal", "Lunch/Dinner"),
            Map.of("id", 26, "name", "Egg & Veggie Breakfast Burrito","category", "Balanced",    "calories", 420, "protein_g", 22, "carbs_g", 44, "fat_g", 16, "fiber_g", 6,  "servings", 1, "meal", "Breakfast"),
            Map.of("id", 27, "name", "Walnut & Apple Salad",         "category", "Low Fat",      "calories", 260, "protein_g", 6,  "carbs_g", 30, "fat_g", 14, "fiber_g", 5,  "servings", 1, "meal", "Lunch/Snack"),
            Map.of("id", 28, "name", "Shrimp Tacos",                 "category", "High Protein", "calories", 460, "protein_g", 34, "carbs_g", 46, "fat_g", 12, "fiber_g", 5,  "servings", 2, "meal", "Lunch/Dinner"),
            Map.of("id", 29, "name", "Milk & Whole Grain Cereal",    "category", "Low Fat",      "calories", 280, "protein_g", 10, "carbs_g", 50, "fat_g", 4,  "fiber_g", 6,  "servings", 1, "meal", "Breakfast"),
            Map.of("id", 30, "name", "Black Bean & Kale Soup",       "category", "Vegetarian",   "calories", 340, "protein_g", 16, "carbs_g", 52, "fat_g", 6,  "fiber_g", 18, "servings", 2, "meal", "Lunch/Dinner")
    ));

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String listRecipes() {
        StringBuilder sb = new StringBuilder("<h2>Recipes</h2><ul>");
        for (Map<String, Object> recipe : RECIPES) {
            sb.append("<li>")
                    .append(recipe.get("id")).append(" - ")
                    .append(recipe.get("name")).append(" (")
                    .append(recipe.get("calories")).append(" cal, ")
                    .append(recipe.get("category")).append(")")
                    .append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getRecipe(@PathVariable int id) {
        return RECIPES.stream()
                .filter(r -> r.get("id").equals(id))
                .findFirst()
                .map(r -> "<h2>" + r.get("name") + "</h2>" +
                        "<p>Category: "  + r.get("category")  + "</p>" +
                        "<p>Calories: "  + r.get("calories")  + " kcal</p>" +
                        "<p>Protein: "   + r.get("protein_g") + "g</p>" +
                        "<p>Carbs: "     + r.get("carbs_g")   + "g</p>" +
                        "<p>Fat: "       + r.get("fat_g")     + "g</p>" +
                        "<p>Fiber: "     + r.get("fiber_g")   + "g</p>" +
                        "<p>Servings: "  + r.get("servings")  + "</p>" +
                        "<p>Best for: "  + r.get("meal")      + "</p>")
                .orElse("<h2>Recipe not found</h2>");
    }

    @PostMapping
    public Map<String, Object> createRecipe(@RequestBody Map<String, Object> body) {
        return Map.of("message", "Recipe created", "received", body);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateRecipe(@PathVariable int id, @RequestBody Map<String, Object> body) {
        return Map.of("message", "Recipe updated", "id", id, "received", body);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteRecipe(@PathVariable int id) {
        return Map.of("message", "Recipe deleted", "id", id);
    }
}