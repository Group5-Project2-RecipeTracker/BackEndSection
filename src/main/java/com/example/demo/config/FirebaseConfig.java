package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class FirebaseConfig {

    @Value("${firebase.credentials.path}")
    private String credentialsPath;

    @PostConstruct
    public void initialize() {
        File credFile = new File(credentialsPath);

        if (!credFile.exists()) {
            System.out.println("Firebase credentials NOT found at: " + credentialsPath);
            return;
        }

        try {
            InputStream is = new FileInputStream(credFile);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(is))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("Firebase initialized");
            }

            // 🔥 TEST FIRESTORE CONNECTION
            var db = com.google.firebase.cloud.FirestoreClient.getFirestore();
            var collections = db.listCollections();

            System.out.println("Firestore connected. Collections:");
            collections.forEach(c -> System.out.println(" - " + c.getId()));

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Firebase: " + credentialsPath, e);
        }
    }
}