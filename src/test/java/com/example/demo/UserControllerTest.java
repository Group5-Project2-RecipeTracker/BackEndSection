import com.example.demo.controller.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private final UserController controller = new UserController();

    @Test
    void profile_returnsGreetingWithPrincipal() {
        String result = controller.profile("testuser");

        assertNotNull(result);
        assertTrue(result.contains("Profile"));
        assertTrue(result.contains("Hello, testuser"));
    }

    @Test
    void profile_withNullPrincipal_returnsGreetingWithNull() {
        String result = controller.profile(null);

        assertNotNull(result);
        assertTrue(result.contains("Hello, null"));
    }
}