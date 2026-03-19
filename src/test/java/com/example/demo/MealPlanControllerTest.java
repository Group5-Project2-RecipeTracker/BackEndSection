import com.example.demo.controller.*;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MealPlanControllerTest {

    private final MealPlanController controller = new MealPlanController();

    @Test
    void getMealPlan_returnsPlaceholderHtml() {
        String result = controller.getMealPlan();

        assertNotNull(result);
        assertTrue(result.contains("Meal Plan"));
        assertTrue(result.contains("Monday"));
        assertTrue(result.contains("Tuesday"));
    }

    @Test
    void addMealEntry_returnsMessageAndBody() {
        Map<String, Object> body = Map.of("day", "Monday", "meal", "Breakfast");

        Map<String, Object> result = controller.addMealEntry(body);

        assertEquals("placeholder: add meal entry", result.get("message"));
        assertEquals(body, result.get("received"));
    }

    @Test
    void deleteMealEntry_returnsMessageAndId() {
        Map<String, Object> result = controller.deleteMealEntry(10);

        assertEquals("placeholder: delete meal entry", result.get("message"));
        assertEquals(10, result.get("id"));
    }
}