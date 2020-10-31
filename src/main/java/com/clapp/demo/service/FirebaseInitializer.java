package com.clapp.demo.service;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseInitializer {

	@PostConstruct
	private void initDB() throws IOException {
		InputStream serviceAccount = this.getClass().getClassLoader()
				.getResourceAsStream("./clapptest1-f246c-firebase-adminsdk-5s1j3-50f4d1e18f.json");

		
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://clapptest1-f246c.firebaseio.com").build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
		
			// TODO Auto-generated catch block
		
		
	}

	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
	
	

}
