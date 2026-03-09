package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping(value = "/api/profile", produces = MediaType.TEXT_HTML_VALUE)
    public String profile(@AuthenticationPrincipal String principal) {
        return "<h2>Profile</h2><p>Hello, " + principal + "</p>";
    }
}