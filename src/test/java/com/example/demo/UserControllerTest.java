package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.controller.UserController;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private final UserController controller = new UserController();

    @Test
    void profile_returnsGreetingWithPrincipal() {
        String result = controller.profile("testuser");
        assertEquals("Hello, testuser", result);
    }

    @Test
    void profile_withNullPrincipal_returnsGreetingWithNull() {
        String result = controller.profile(null);
        assertEquals("Hello, null", result);
    }
}
