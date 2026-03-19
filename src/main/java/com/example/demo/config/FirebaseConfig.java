package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try (InputStream is =
                     new ClassPathResource("firebase-service-account.json").getInputStream()) {

            List<FirebaseApp> apps = FirebaseApp.getApps();

            if (apps == null || apps.isEmpty()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(is))
                        .build();

                FirebaseApp.initializeApp(options);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
