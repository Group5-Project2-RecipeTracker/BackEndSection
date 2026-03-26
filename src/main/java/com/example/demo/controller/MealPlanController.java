package com.example.demo.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/meal-plans")
public class MealPlanController {

    private Firestore getDb() {
        return FirestoreClient.getFirestore();
    }

    private String requireUid(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing authenticated user");
        }
        return authentication.getPrincipal().toString();
    }

    @GetMapping
    public Map<String, Object> getCurrentUsersMealPlan(Authentication authentication) throws Exception {
        String uid = requireUid(authentication);
        DocumentReference docRef = getDb().collection("mealPlans").document(uid);
        DocumentSnapshot doc = docRef.get().get();

        if (!doc.exists()) {
            return defaultMealPlan(uid);
        }

        Map<String, Object> data = doc.getData();
        if (data == null) {
            return defaultMealPlan(uid);
        }
        data.putIfAbsent("userId", uid);
        data.putIfAbsent("meals", emptyMeals());
        return data;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveCurrentUsersMealPlan(
            Authentication authentication,
            @RequestBody Map<String, Object> body
    ) throws Exception {
        String uid = requireUid(authentication);

        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("userId", uid);
        payload.put("meals", normalizeMeals(body.get("meals")));

        Object updatedAt = body.get("updatedAt");
        if (updatedAt != null) {
            payload.put("updatedAt", updatedAt);
        }

        getDb().collection("mealPlans").document(uid).set(payload).get();
        return ResponseEntity.ok(payload);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateCurrentUsersMealPlan(
            Authentication authentication,
            @RequestBody Map<String, Object> body
    ) throws Exception {
        return saveCurrentUsersMealPlan(authentication, body);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCurrentUsersMealPlan(Authentication authentication) throws Exception {
        String uid = requireUid(authentication);
        getDb().collection("mealPlans").document(uid).delete().get();
        return ResponseEntity.noContent().build();
    }

    private Map<String, Object> defaultMealPlan(String uid) {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("userId", uid);
        payload.put("meals", emptyMeals());
        return payload;
    }

    private Map<String, Object> emptyMeals() {
        Map<String, Object> meals = new LinkedHashMap<>();
        meals.put("breakfast", List.of());
        meals.put("lunch", List.of());
        meals.put("dinner", List.of());
        meals.put("snack", List.of());
        return meals;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> normalizeMeals(Object mealsObject) {
        Map<String, Object> meals = new LinkedHashMap<>(emptyMeals());
        if (mealsObject instanceof Map<?, ?> incomingMeals) {
            incomingMeals.forEach((key, value) -> {
                if (key != null && value instanceof List<?>) {
                    meals.put(key.toString(), value);
                }
            });
        }
        return meals;
    }
}