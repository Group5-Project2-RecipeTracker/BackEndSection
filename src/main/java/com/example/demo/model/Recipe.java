package com.example.demo.model;

import java.util.List;

public class Recipe {

    private String id;
    private String name;
    private Integer index;
    private String description;
    private String category;
    private String pictureURL;
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private List<Ingredient> ingredients;
    private List<String> instructions;

    public Recipe() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getProtein() {
        return protein;
    }

    public Integer getCarbs() {
        return carbs;
    }

    public Integer getFat() {
        return fat;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setProtein(Integer protein) {
        this.protein = protein;
    }

    public void setCarbs(Integer carbs) {
        this.carbs = carbs;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}