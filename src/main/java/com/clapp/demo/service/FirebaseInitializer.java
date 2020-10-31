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
					.setDatabaseUrl("https://clappauth.firebaseio.com").build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
		
			// TODO Auto-generated catch block
		
		
	}

	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
	
	private InputStream createFirebaseCredential() throws Exception {
	    //private key
	    String privateKey = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWuiHEqlFRJ7M/\nKKiQlViX6ep3b1o6pQR5TuMx04synB7zu1exGNa6ckqDzGEUxnjGUKKmDNs3agFU\nGjEolwEuzGc/HQlIDhctrFpg4jFfdj3lWNr6bQe1wHZMfqDMBXgBAUvTAXDSctpC\nUvIYNM615gJ3xAQu2KP7Dywx5fp7Oobs+MSEIr/xCPRZE8+6blRvqtI8KEedcO6l\nBxEUCvJ/BYuH3HokrQ8kBe/Y2d56yLXcGZwOs+mUEpSKweW69knGA/Hp7ch+sH03\nZqkpjl/difuDOIbRD8aECuOMs/L9s8AGQBIdHQwfsVzDcNB59sFgfuo01EV+Rai3\nu0blUScBAgMBAAECggEAQePthiU0didGRxp1kfsPpx1VZ8/QYGmqCoPPcwU4yS+R\nolfoDl2+5PO4mmQ6nlPjEULHz5I4NMHNAeCPU+CrXjNyARem6o5qWIeLXOYU0lgE\n1+FfnPET2ULhFDCZB4duffX8mSn8kasuuaa7+ZQAN2ls88FHWNlQ2hw4ZDnWpY50\nqd67qvY7++vvUSclnBOPF14+2a+idSAcWc+ZXyo3412peqfEIUihgZ2uKmlmhWSJ\nz7gweOJERgv/TzfFW9sjjf/Pi81JYgk8kC36M+VW1M0tDVPOhj0qyc9As9qrzLNN\nvotLqR3jliHVuqFcCXK2JAYET+QFImCb7hEZZhR4BwKBgQDSr8aMAH/yjPlDaAGF\nNebSE+VQMNWFOTKwAn2uWVtT6Po+rsS5LuuAycSXb2yMZgVhy0g6NX73ePsIXQsD\ntPYRYtdLssCnDRaRvUHyurQEurXVzWVFBVPJ6K1flAwQnoZB4tvzOlGMG/PHzRjj\nZsgUK3qwSL/aaYKjd5AplFxRxwKBgQC3JQkIJzB0QcxsBnRiMesZaSP+z/koEBLk\n1mPqyNf422pDtWNy8WXMZifhgnkO4ODE8TC9tceRfK32JxVd+OWX0FCYwxLClKnV\nTeFxMBbLVHmIaaVrNjxferW38tpvmrOACvqNyCF1Y9fs24JVNkG5+VRyaezwzrfS\nTGM3hc/A9wKBgFzeTk54Ce4IZfdoaQMm90+ypWnrnLLUu5sJ/gxUJwWdtHDc6KoY\nHIbwaU+3NcnNTqIE0RmXaxf6o+zT1n8TzUNspiof6Pu5sTEck21DDwbmpjBQEUuT\nRZPClGmFAKxytAtmzD0yf6+awqJXrZjPPXJml/YMm0TTLVqQ4WLymp8RAoGBAJVf\nc3pe3Do14FDLAumD5UgYZO9TGNgAJCjYYy3/GYczDfyjWLVLBcgsGaLoE2x7JVf7\nj9FrDZYvpoL5ZYf7lOa5VMMDd62UKuB6YME8g5rbDzSfVfeXiK4nG0con9geyQBJ\n9AjnjcJ86IOalTaCxiC0ppeapVHSiELRYqrKTKQJAoGAayfT0XFuhsqXGccArwhV\nc5TFDyvqcDTvjabp1TRwVn+ejh+D0qqYBBeK54lcFEfp6knskH7VM+ounDCX0E7j\nSotlvwbdegAQ33m5VzqHyE9bRL2NFtTr1EXV/xdwXpAvqkY8A2Jf0j0vQcaoZR53\nR2p+su+DjFxU/mWAouhbotg=\n-----END PRIVATE KEY-----\n".replace("\\n", "\n");

	    FirebaseCredential firebaseCredential = new FirebaseCredential();
	    firebaseCredential.setType("service_account");
	    firebaseCredential.setProject_id("clappauth");
	    firebaseCredential.setPrivate_key_id("aa03b4f0bf20e45a1043c7c652e3f5910bf26308");
	    firebaseCredential.setPrivate_key(privateKey);
	    firebaseCredential.setClient_email("firebase-adminsdk-gyzjx@clappauth.iam.gserviceaccount.com");
	    firebaseCredential.setClient_id("100869880468745293116");
	    firebaseCredential.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
	    firebaseCredential.setToken_uri("https://oauth2.googleapis.com/token");
	    firebaseCredential.setAuth_provider_x509_cert_url("https://www.googleapis.com/oauth2/v1/certs");
	    firebaseCredential.setClient_x509_cert_url( "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-gyzjx%40clappauth.iam.gserviceaccount.com");
	    //serialization of the object to json string
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString = mapper.writeValueAsString(firebaseCredential);

	    //convert jsonString string to InputStream using Apache Commons
	    return IOUtils.toInputStream(jsonString);
	}

}
