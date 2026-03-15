package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private static final List<Map<String, Object>> FOODS = new ArrayList<>(List.of(
            Map.of("id", 1,  "name", "Chicken Breast",     "category", "Protein",   "calories", 200, "protein_g", 38,  "carbs_g", 0,  "fat_g", 4,  "fiber_g", 0,  "meal", "Lunch/Dinner"),
            Map.of("id", 2,  "name", "Brown Rice",          "category", "Carb",      "calories", 215, "protein_g", 5,   "carbs_g", 45, "fat_g", 2,  "fiber_g", 4,  "meal", "Lunch/Dinner"),
            Map.of("id", 3,  "name", "Scrambled Eggs",      "category", "Protein",   "calories", 180, "protein_g", 12,  "carbs_g", 2,  "fat_g", 13, "fiber_g", 0,  "meal", "Breakfast"),
            Map.of("id", 4,  "name", "Oatmeal",             "category", "Carb",      "calories", 150, "protein_g", 5,   "carbs_g", 27, "fat_g", 3,  "fiber_g", 4,  "meal", "Breakfast"),
            Map.of("id", 5,  "name", "Salmon Fillet",       "category", "Protein",   "calories", 350, "protein_g", 40,  "carbs_g", 0,  "fat_g", 20, "fiber_g", 0,  "meal", "Dinner"),
            Map.of("id", 6,  "name", "Broccoli",            "category", "Vegetable", "calories",  55, "protein_g", 4,   "carbs_g", 11, "fat_g", 1,  "fiber_g", 5,  "meal", "Lunch/Dinner"),
            Map.of("id", 7,  "name", "Sweet Potato",        "category", "Carb",      "calories", 180, "protein_g", 4,   "carbs_g", 41, "fat_g", 0,  "fiber_g", 7,  "meal", "Lunch/Dinner"),
            Map.of("id", 8,  "name", "Greek Yogurt",        "category", "Dairy",     "calories", 100, "protein_g", 17,  "carbs_g", 6,  "fat_g", 1,  "fiber_g", 0,  "meal", "Breakfast/Snack"),
            Map.of("id", 9,  "name", "Banana",              "category", "Fruit",     "calories",  89, "protein_g", 1,   "carbs_g", 23, "fat_g", 0,  "fiber_g", 3,  "meal", "Snack"),
            Map.of("id", 10, "name", "Almonds",             "category", "Snack",     "calories", 160, "protein_g", 6,   "carbs_g", 6,  "fat_g", 14, "fiber_g", 4,  "meal", "Snack"),
            Map.of("id", 11, "name", "Spinach Salad",       "category", "Vegetable", "calories",  40, "protein_g", 3,   "carbs_g", 6,  "fat_g", 1,  "fiber_g", 3,  "meal", "Lunch"),
            Map.of("id", 12, "name", "Whole Wheat Bread",   "category", "Carb",      "calories", 120, "protein_g", 5,   "carbs_g", 23, "fat_g", 2,  "fiber_g", 3,  "meal", "Breakfast/Lunch"),
            Map.of("id", 13, "name", "Ground Turkey",       "category", "Protein",   "calories", 220, "protein_g", 30,  "carbs_g", 0,  "fat_g", 11, "fiber_g", 0,  "meal", "Lunch/Dinner"),
            Map.of("id", 14, "name", "Apple",               "category", "Fruit",     "calories",  95, "protein_g", 0,   "carbs_g", 25, "fat_g", 0,  "fiber_g", 4,  "meal", "Snack"),
            Map.of("id", 15, "name", "Cottage Cheese",      "category", "Dairy",     "calories", 110, "protein_g", 14,  "carbs_g", 4,  "fat_g", 5,  "fiber_g", 0,  "meal", "Breakfast/Snack"),
            Map.of("id", 16, "name", "Tuna Can",            "category", "Protein",   "calories", 130, "protein_g", 28,  "carbs_g", 0,  "fat_g", 1,  "fiber_g", 0,  "meal", "Lunch"),
            Map.of("id", 17, "name", "Avocado",             "category", "Fruit",     "calories", 240, "protein_g", 3,   "carbs_g", 13, "fat_g", 22, "fiber_g", 10, "meal", "Lunch/Dinner"),
            Map.of("id", 18, "name", "Quinoa",              "category", "Carb",      "calories", 222, "protein_g", 8,   "carbs_g", 39, "fat_g", 4,  "fiber_g", 5,  "meal", "Lunch/Dinner"),
            Map.of("id", 19, "name", "Baby Carrots",        "category", "Vegetable", "calories",  35, "protein_g", 1,   "carbs_g", 8,  "fat_g", 0,  "fiber_g", 2,  "meal", "Snack"),
            Map.of("id", 20, "name", "Cheddar Cheese",      "category", "Dairy",     "calories", 110, "protein_g", 7,   "carbs_g", 0,  "fat_g", 9,  "fiber_g", 0,  "meal", "Snack/Lunch"),
            Map.of("id", 21, "name", "Blueberries",         "category", "Fruit",     "calories",  84, "protein_g", 1,   "carbs_g", 21, "fat_g", 0,  "fiber_g", 4,  "meal", "Breakfast/Snack"),
            Map.of("id", 22, "name", "Steak (Sirloin)",     "category", "Protein",   "calories", 400, "protein_g", 42,  "carbs_g", 0,  "fat_g", 24, "fiber_g", 0,  "meal", "Dinner"),
            Map.of("id", 23, "name", "Pasta (Whole Wheat)", "category", "Carb",      "calories", 280, "protein_g", 10,  "carbs_g", 54, "fat_g", 2,  "fiber_g", 7,  "meal", "Lunch/Dinner"),
            Map.of("id", 24, "name", "Kale",                "category", "Vegetable", "calories",  33, "protein_g", 3,   "carbs_g", 6,  "fat_g", 1,  "fiber_g", 2,  "meal", "Lunch/Dinner"),
            Map.of("id", 25, "name", "Peanut Butter",       "category", "Snack",     "calories", 190, "protein_g", 8,   "carbs_g", 6,  "fat_g", 16, "fiber_g", 2,  "meal", "Breakfast/Snack"),
            Map.of("id", 26, "name", "Shrimp",              "category", "Protein",   "calories", 100, "protein_g", 20,  "carbs_g", 0,  "fat_g", 2,  "fiber_g", 0,  "meal", "Lunch/Dinner"),
            Map.of("id", 27, "name", "Strawberries",        "category", "Fruit",     "calories",  49, "protein_g", 1,   "carbs_g", 12, "fat_g", 0,  "fiber_g", 3,  "meal", "Breakfast/Snack"),
            Map.of("id", 28, "name", "Black Beans",         "category", "Protein",   "calories", 227, "protein_g", 15,  "carbs_g", 41, "fat_g", 1,  "fiber_g", 15, "meal", "Lunch/Dinner"),
            Map.of("id", 29, "name", "Milk (2%)",           "category", "Dairy",     "calories", 122, "protein_g", 8,   "carbs_g", 12, "fat_g", 5,  "fiber_g", 0,  "meal", "Breakfast/Snack"),
            Map.of("id", 30, "name", "Walnuts",             "category", "Snack",     "calories", 185, "protein_g", 4,   "carbs_g", 4,  "fat_g", 18, "fiber_g", 2,  "meal", "Snack")
    ));

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String listFoods() {
        StringBuilder sb = new StringBuilder("<h2>Foods</h2><ul>");
        for (Map<String, Object> food : FOODS) {
            sb.append("<li>")
                    .append(food.get("id")).append(" - ")
                    .append(food.get("name")).append(" (")
                    .append(food.get("calories")).append(" cal, ")
                    .append(food.get("category")).append(")")
                    .append("</li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getFood(@PathVariable int id) {
        return FOODS.stream()
                .filter(f -> f.get("id").equals(id))
                .findFirst()
                .map(f -> "<h2>" + f.get("name") + "</h2>" +
                        "<p>Category: "  + f.get("category")  + "</p>" +
                        "<p>Calories: "  + f.get("calories")  + " kcal</p>" +
                        "<p>Protein: "   + f.get("protein_g") + "g</p>" +
                        "<p>Carbs: "     + f.get("carbs_g")   + "g</p>" +
                        "<p>Fat: "       + f.get("fat_g")     + "g</p>" +
                        "<p>Fiber: "     + f.get("fiber_g")   + "g</p>" +
                        "<p>Best for: "  + f.get("meal")      + "</p>")
                .orElse("<h2>Food not found</h2>");
    }

    @PostMapping
    public Map<String, Object> createFood(@RequestBody Map<String, Object> body) {
        return Map.of("message", "Food created", "received", body);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateFood(@PathVariable int id, @RequestBody Map<String, Object> body) {
        return Map.of("message", "Food updated", "id", id, "received", body);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteFood(@PathVariable int id) {
        return Map.of("message", "Food deleted", "id", id);
    }
}