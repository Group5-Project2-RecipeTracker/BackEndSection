package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/profile")
    public Map<String, Object> profile(Authentication authentication) {
        return Map.of(
                "message", "Current authenticated user profile",
                "uid", authentication == null ? "anonymous" : String.valueOf(authentication.getPrincipal()),
                "email", authentication == null || authentication.getDetails() == null ? "" : String.valueOf(authentication.getDetails())
        );
    }

    @GetMapping("/{userId}")
    public Map<String, Object> getUser(@PathVariable String userId) {
        return Map.of(
                "message", "Get one user",
                "userId", userId
        );
    }

    @PutMapping("/{userId}")
    public Map<String, Object> updateUser(
            @PathVariable String userId,
            @RequestBody Map<String, Object> body
    ) {
        return Map.of(
                "message", "Update user",
                "userId", userId,
                "received", body
        );
    }
}