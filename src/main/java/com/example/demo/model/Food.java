package com.example.demo.model;

public class Food {

    private String id;
    private String name;
    private Integer index;
    private String category;
    private Integer calories;
    private Integer protein;
    private Integer carbs;
    private Integer fat;
    private String pictureURL;

    public Food() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getIndex() {
        return index;
    }

    public String getCategory() {
        return category;
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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}