package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String stats() {
        return """
            <h2>Stats (placeholder)</h2>
            <ul>
              <li>mealsLogged: 15</li>
              <li>recipesSaved: 8</li>
              <li>favorites: 5</li>
            </ul>
        """;
    }
}