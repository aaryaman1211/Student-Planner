package com.studentplanner.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initFirebase() throws IOException {
        InputStream serviceAccount;

        String credentials = System.getenv("FIREBASE_CREDENTIALS");
        if (credentials != null) {
            // On Railway — reads from environment variable
            serviceAccount = new ByteArrayInputStream(credentials.getBytes());
        } else {
            // Local — reads from file
            serviceAccount = new FileInputStream(
                "src/main/resources/serviceAccountKey.json"
            );
        }

        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
}