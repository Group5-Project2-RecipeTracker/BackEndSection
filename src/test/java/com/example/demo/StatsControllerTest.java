package com.example.demo;
import com.example.demo.controller.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsControllerTest {

    private final StatsController controller = new StatsController();

    @Test
    void stats_returnsPlaceholderHtml() {
        String result = controller.stats();

        assertNotNull(result);
        assertTrue(result.contains("Stats"));
        assertTrue(result.contains("mealsLogged"));
        assertTrue(result.contains("recipesSaved"));
        assertTrue(result.contains("favorites"));
    }
}