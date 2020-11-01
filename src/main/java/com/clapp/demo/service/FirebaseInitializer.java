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
		//InputStream serviceAccount = this.getClass().getClassLoader()
				//.getResourceAsStream("./clappauth-firebase-adminsdk-gyzjx-aa03b4f0bf.json");

		InputStream serviceAccount;
		try {
			
			serviceAccount = createFirebaseCredential2();
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://clapptest1-f246c.firebaseio.com").build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Firestore getFirebase() {
		return FirestoreClient.getFirestore();
	}
	
	private InputStream createFirebaseCredential2() throws Exception {
	    //private key
	    String privateKey = "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJ0B959+XYdO29\nq2aazRb9y5VLZGZqDLW5/JkRWA/8XFlGepTNlc0hPDzsUbHie4dWOV7ittoFlbRE\n6Rp4PRhC+p3qdpciGcIyn3Ub5vuRobC0CQ7Jl855xQQIA+fdYNzcDN79+ecTsKRn\nUJed4G5UNcpt7+r+hR51On75aMcCvvDpltjIc7Hp8/OnyP2cGAVntQ4T+FEPsBon\n2eva69YxV8mrF9d2IwHDNU7J38AqS7NfYEG5ivgH0sKPnwxxUejF750trE2MbDgY\nm636Z8+uq++RH7ztSu1VYYJV5UuYQmBlKmVPeB1Go0HwjzUm1thT3Ame3LNNGt34\ni31wGYBVAgMBAAECggEAI0r03KfzKVlcjw/HkYpHQoyx+aJHJg6GNZCuZIYwZdkC\nle2LkRC1fTbvhTewvgcTlC3SEm5wbDC3/1PRDwzaW0DSUzq2nqmJFazHcXNYZqXJ\nWnndWxww6Pde/mx9ozCMrx/utY0bs+uvVrQJeAmDR7aU1k20WSUfNTdbUCUYnieG\n0xABv+Z+qq8q17S6WOxAWKSH/WDwlm9E3GoLWzzHjuSSyrWrB2gQ/+wRZX9P4lJl\n2CjKojJ8/6rzmYqD704Q0OEyg1p6k6bQDftfmadFbSptnUveZTYRQc7D8ns43LJk\n0VoaLpGmQqrXvHpVi3iDhNLqVZ37mTkuihNtd9+R2wKBgQC/XbAFfomLWWfCkqB6\noGC2XIOsbxtkk0BtLHlmBKDBEZuaJw+iDTllg2Wt3d4Psc5eomKuboRYgbT1aZi4\nirl6z4Ft9hOIX5YjE9wi/ktAoyTf78CxexIDGno8VgVrsG0mPjl6crkFeD+aecgf\n1b4f4fIlEzUs1Js8pUECBApJcwKBgQC4XARnZ7cmpVMo+kIkxTUqWLAh6Q+A5I5Q\ni6ZTCkA1zwV9M/Yu+M0M5qnVkSAmy1rwvu7nsjYIymXGy7Wht5H30CkFYJEPk+FO\nIwbTsC0Tnjvf1sug4Rzdcy3mH16ZfbK3g23ogr2ZzafSVeIiXmdrPIN9IwVlpJdG\nrCILqIG9FwKBgHa22AuQhCM+Q3AhJgzskRKQMd82zfeGoeEYRfdTx6X+Fll7Aee1\n7HVdHibODIQP+cYbVrx/Mvr0/B2CdIuHA6l8MP7WOSfQpIL+PwI2XtiqXKXPRerU\nPkhOlKX5d0NnH08qKt4bbUxHXK2n/s5A+sEsmdsGH2bAPc1RdxKZDwihAoGABSXc\noZ6NyLB5H/3M9wXitCoB0RCW9DxtlOYxYMQ6gsYjGxnmV+1unFuchnAk+7BV+775\nINMjGx+7s89gZQGhO9A+NP47TroMYogr2qFiwNd8SIXQ7c8bZTugHnrlw1d1NBah\nbQqpjK/nbcIkLF2CecYYzde6gNoVt017LeQoq0UCgYEAspZ4sTihZVKkjcuARm46\nTiatufLAA3vmX6Ey7KibLX+6CjjZHjBZYJHU7HtoK/9ioK4ASnrIClSaeZCISSzn\nTpnxBg6e6VMNQ8CxFPtS+YuapNB7NTr/IshSHYdKSXPySxERLd4vpW3em9uNAe31\nVtSLH2tqWubX4xZHd0gkZeg=\n-----END PRIVATE KEY-----\n";

	    FirebaseCredential firebaseCredential = new FirebaseCredential();
	    firebaseCredential.setType("service_account");
	    firebaseCredential.setProject_id("clapptest1-f246c");
	    firebaseCredential.setPrivate_key_id("50f4d1e18f2e245f46a420da0d3bfe138df93687");
	    firebaseCredential.setPrivate_key(privateKey);
	    firebaseCredential.setClient_email("firebase-adminsdk-5s1j3@clapptest1-f246c.iam.gserviceaccount.com");
	    firebaseCredential.setClient_id("100295374725315631718");
	    firebaseCredential.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
	    firebaseCredential.setToken_uri("https://oauth2.googleapis.com/token");
	    firebaseCredential.setAuth_provider_x509_cert_url("https://www.googleapis.com/oauth2/v1/certs");
	    firebaseCredential.setClient_x509_cert_url( "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-5s1j3%40clapptest1-f246c.iam.gserviceaccount.com");
	    //serialization of the object to json string
	    ObjectMapper mapper = new ObjectMapper();
	    String jsonString = mapper.writeValueAsString(firebaseCredential);

	    //convert jsonString string to InputStream using Apache Commons
	    return IOUtils.toInputStream(jsonString);
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
