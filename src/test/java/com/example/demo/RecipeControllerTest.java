import com.example.demo.controller.*;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {

    private final RecipeController controller = new RecipeController();

    @Test
    void listRecipes_returnsHtmlList() {
        String result = controller.listRecipes();

        assertNotNull(result);
        assertTrue(result.contains("Recipes"));
        assertTrue(result.contains("Grilled Chicken Bowl"));
        assertTrue(result.contains("Veggie Stir Fry"));
    }

    @Test
    void getRecipe_withValidId_returnsRecipeDetails() {
        String result = controller.getRecipe(1);

        assertTrue(result.contains("Grilled Chicken Bowl"));
        assertTrue(result.contains("Calories"));
        assertTrue(result.contains("Servings"));
    }

    @Test
    void getRecipe_withInvalidId_returnsNotFoundMessage() {
        String result = controller.getRecipe(999);

        assertEquals("<h2>Recipe not found</h2>", result);
    }

    @Test
    void createRecipe_returnsCreatedMessage() {
        Map<String, Object> body = Map.of("name", "Test Recipe", "calories", 400);

        Map<String, Object> result = controller.createRecipe(body);

        assertEquals("Recipe created", result.get("message"));
        assertEquals(body, result.get("received"));
    }

    @Test
    void updateRecipe_returnsUpdatedMessage() {
        Map<String, Object> body = Map.of("name", "Updated Recipe");

        Map<String, Object> result = controller.updateRecipe(4, body);

        assertEquals("Recipe updated", result.get("message"));
        assertEquals(4, result.get("id"));
        assertEquals(body, result.get("received"));
    }

    @Test
    void deleteRecipe_returnsDeletedMessage() {
        Map<String, Object> result = controller.deleteRecipe(8);

        assertEquals("Recipe deleted", result.get("message"));
        assertEquals(8, result.get("id"));
    }
}