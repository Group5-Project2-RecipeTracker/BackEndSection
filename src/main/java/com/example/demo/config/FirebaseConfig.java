package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public Firestore firestore() {
        try (InputStream is = new ClassPathResource("firebase-service-account.json").getInputStream()) {

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(is))
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized");
            }

            Firestore db = FirestoreClient.getFirestore();
            System.out.println("Firestore connected");
            return db;

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Firebase from classpath resource", e);
        }
    }
}
