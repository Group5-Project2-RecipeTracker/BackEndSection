package com.example.demo;

import com.example.demo.controller.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FavoritesControllerTest {

    private final FavoritesController controller = new FavoritesController();

    @Test
    void listFavorites_returnsPlaceholderHtml() {
        String result = controller.listFavorites();

        assertNotNull(result);
        assertTrue(result.contains("Favorites"));
        assertTrue(result.contains("Chicken Bowl"));
        assertTrue(result.contains("Oatmeal"));
    }

    @Test
    void addFavorite_returnsMessageAndId() {
        Map<String, Object> result = controller.addFavorite(7);

        assertEquals("placeholder: add favorite", result.get("message"));
        assertEquals(7, result.get("id"));
    }

    @Test
    void removeFavorite_returnsMessageAndId() {
        Map<String, Object> result = controller.removeFavorite(3);

        assertEquals("placeholder: remove favorite", result.get("message"));
        assertEquals(3, result.get("id"));
    }
}