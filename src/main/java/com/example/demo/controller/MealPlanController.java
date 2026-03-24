package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {

    @GetMapping
    public Map<String, Object> listMealPlans(
            @RequestParam(required = false) String userId
    ) {
        return Map.of(
                "message", "List meal plans from Firestore",
                "collection", "mealPlans",
                "userId", userId == null ? "" : userId,
                "routes", List.of(
                        "GET /api/meal-plans",
                        "GET /api/meal-plans/{mealPlanId}",
                        "POST /api/meal-plans",
                        "PUT /api/meal-plans/{mealPlanId}",
                        "DELETE /api/meal-plans/{mealPlanId}"
                )
        );
    }

    @GetMapping("/{mealPlanId}")
    public Map<String, Object> getMealPlan(@PathVariable String mealPlanId) {
        return Map.of(
                "message", "Get one meal plan from Firestore",
                "mealPlanId", mealPlanId,
                "collection", "mealPlans"
        );
    }

    @PostMapping
    public Map<String, Object> createMealPlan(@RequestBody Map<String, Object> body) {
        return Map.of(
                "message", "Create meal plan in Firestore",
                "collection", "mealPlans",
                "received", body
        );
    }

    @PutMapping("/{mealPlanId}")
    public Map<String, Object> updateMealPlan(
            @PathVariable String mealPlanId,
            @RequestBody Map<String, Object> body
    ) {
        return Map.of(
                "message", "Update meal plan in Firestore",
                "mealPlanId", mealPlanId,
                "collection", "mealPlans",
                "received", body
        );
    }

    @DeleteMapping("/{mealPlanId}")
    public Map<String, Object> deleteMealPlan(@PathVariable String mealPlanId) {
        return Map.of(
                "message", "Delete meal plan from Firestore",
                "mealPlanId", mealPlanId,
                "collection", "mealPlans"
        );
    }
}