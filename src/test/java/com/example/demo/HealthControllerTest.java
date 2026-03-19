package com.example.demo;
import com.example.demo.controller.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    private final HealthController controller = new HealthController();

    @Test
    void health_returnsOkStatusHtml() {
        String result = controller.health();

        assertNotNull(result);
        assertTrue(result.contains("Status: OK"));
        assertTrue(result.contains("timestamp:"));
    }
}