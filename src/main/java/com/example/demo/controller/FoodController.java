package com.example.demo.controller;

import com.example.demo.model.Food;
import com.example.demo.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<Food>> listFoods() throws Exception {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFood(@PathVariable String id) throws Exception {
        Food food = foodService.getFoodById(id);

        if (food == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Food not found", "id", id));
        }

        return ResponseEntity.ok(food);
    }

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody Food food) throws Exception {
        return ResponseEntity.ok(foodService.createFood(food));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable String id, @RequestBody Food food) throws Exception {
        Food updated = foodService.updateFood(id, food);

        if (updated == null) {
            return ResponseEntity.status(404).body(Map.of("message", "Food not found", "id", id));
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFood(@PathVariable String id) throws Exception {
        boolean deleted = foodService.deleteFood(id);

        if (!deleted) {
            return ResponseEntity.status(404).body(Map.of("message", "Food not found", "id", id));
        }

        return ResponseEntity.ok(Map.of("message", "Food deleted", "id", id));
    }
}