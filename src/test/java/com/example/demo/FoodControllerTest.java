package com.example.demo;

import com.example.demo.controller.*;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FoodControllerTest {

    private final FoodController controller = new FoodController();

    @Test
    void listFoods_returnsHtmlList() {
        String result = controller.listFoods();

        assertNotNull(result);
        assertTrue(result.contains("Foods"));
        assertTrue(result.contains("Chicken Breast"));
        assertTrue(result.contains("Brown Rice"));
    }

    @Test
    void getFood_withValidId_returnsFoodDetails() {
        String result = controller.getFood(1);

        assertTrue(result.contains("Chicken Breast"));
        assertTrue(result.contains("Calories"));
        assertTrue(result.contains("Protein"));
    }

    @Test
    void getFood_withInvalidId_returnsNotFoundMessage() {
        String result = controller.getFood(999);

        assertEquals("<h2>Food not found</h2>", result);
    }

    @Test
    void createFood_returnsCreatedMessage() {
        Map<String, Object> body = Map.of("name", "Test Food", "calories", 123);

        Map<String, Object> result = controller.createFood(body);

        assertEquals("Food created", result.get("message"));
        assertEquals(body, result.get("received"));
    }

    @Test
    void updateFood_returnsUpdatedMessage() {
        Map<String, Object> body = Map.of("name", "Updated Food");

        Map<String, Object> result = controller.updateFood(5, body);

        assertEquals("Food updated", result.get("message"));
        assertEquals(5, result.get("id"));
        assertEquals(body, result.get("received"));
    }

    @Test
    void deleteFood_returnsDeletedMessage() {
        Map<String, Object> result = controller.deleteFood(2);

        assertEquals("Food deleted", result.get("message"));
        assertEquals(2, result.get("id"));
    }
}