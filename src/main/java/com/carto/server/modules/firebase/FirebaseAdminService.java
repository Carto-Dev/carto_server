package com.carto.server.modules.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class FirebaseAdminService {

    @PostConstruct
    public void initializeFirebaseAdmin() {
        try {

            String serviceAccountKey = System.getenv("FIREBASE_ADMIN_JSON");
            InputStream serviceAccountStream =
                    new ByteArrayInputStream(serviceAccountKey.getBytes(StandardCharsets.UTF_8));

            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(serviceAccountStream);
            FirebaseOptions firebaseOptions = FirebaseOptions
                    .builder()
                    .setCredentials(googleCredentials)
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);

            log.info("Firebase Admin Has Been Initialized");

        } catch (Exception exception) {

            exception.printStackTrace();

        }
    }

}
