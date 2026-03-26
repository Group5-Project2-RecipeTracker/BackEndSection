package com.example.demo.service;

import com.example.demo.model.Food;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FoodService {

    private final Firestore db;
    public FoodService(Firestore db) {
        this.db = db;
    }

    private static final String COLLECTION = "foods";

    public List<Food> getAllFoods() throws Exception {
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Food> foods = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Food food = document.toObject(Food.class);
            food.setId(document.getId());
            foods.add(food);
        }

        foods.sort(Comparator.comparing(
                food -> food.getIndex() == null ? Integer.MAX_VALUE : food.getIndex()
        ));

        return foods;
    }

    public Food getFoodById(String foodId) throws Exception {
        DocumentReference ref = db.collection(COLLECTION).document(foodId);
        DocumentSnapshot snapshot = ref.get().get();

        if (!snapshot.exists()) {
            return null;
        }

        Food food = snapshot.toObject(Food.class);
        if (food != null) {
            food.setId(snapshot.getId());
        }
        return food;
    }

    public Food createFood(Food food) throws Exception {
        DocumentReference ref;

        if (food.getId() != null && !food.getId().isBlank()) {
            ref = db.collection(COLLECTION).document(food.getId());
            ref.set(food).get();
        } else {
            ref = db.collection(COLLECTION).document();
            ref.set(food).get();
        }

        return getFoodById(ref.getId());
    }

    public Food updateFood(String foodId, Food food) throws Exception {
        DocumentReference ref = db.collection(COLLECTION).document(foodId);

        if (!ref.get().get().exists()) {
            return null;
        }

        food.setId(foodId);
        ref.set(food).get();
        return getFoodById(foodId);
    }

    public boolean deleteFood(String foodId) throws Exception {
        DocumentReference ref = db.collection(COLLECTION).document(foodId);

        if (!ref.get().get().exists()) {
            return false;
        }

        ref.delete().get();
        return true;
    }
}