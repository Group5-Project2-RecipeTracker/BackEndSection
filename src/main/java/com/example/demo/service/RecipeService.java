package com.example.demo.service;

import com.example.demo.model.Recipe;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RecipeService {

    private final Firestore db;
    public RecipeService(Firestore db) {
        this.db = db;
    }

    private static final String COLLECTION = "recipes";

    public List<Recipe> getAllRecipes() throws Exception {
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Recipe> recipes = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            Recipe recipe = document.toObject(Recipe.class);
            recipe.setId(document.getId());
            recipes.add(recipe);
        }

        recipes.sort(Comparator.comparing(
                recipe -> recipe.getIndex() == null ? Integer.MAX_VALUE : recipe.getIndex()
        ));

        return recipes;
    }

    public Recipe getRecipeById(String recipeId) throws Exception {
        DocumentReference ref = db.collection(COLLECTION).document(recipeId);
        DocumentSnapshot snapshot = ref.get().get();

        if (!snapshot.exists()) {
            return null;
        }

        Recipe recipe = snapshot.toObject(Recipe.class);
        if (recipe != null) {
            recipe.setId(snapshot.getId());
        }
        return recipe;
    }

    public Recipe createRecipe(Recipe recipe) throws Exception {
        DocumentReference ref;

        if (recipe.getId() != null && !recipe.getId().isBlank()) {
            ref = db.collection(COLLECTION).document(recipe.getId());
            ref.set(recipe).get();
        } else {
            ref = db.collection(COLLECTION).document();
            ref.set(recipe).get();
        }

        return getRecipeById(ref.getId());
    }

    public Recipe updateRecipe(String recipeId, Recipe recipe) throws Exception {
        DocumentReference ref = db.collection(COLLECTION).document(recipeId);

        if (!ref.get().get().exists()) {
            return null;
        }

        recipe.setId(recipeId);
        ref.set(recipe).get();
        return getRecipeById(recipeId);
    }

    public boolean deleteRecipe(String recipeId) throws Exception {
        DocumentReference ref = db.collection(COLLECTION).document(recipeId);

        if (!ref.get().get().exists()) {
            return false;
        }

        ref.delete().get();
        return true;
    }
}