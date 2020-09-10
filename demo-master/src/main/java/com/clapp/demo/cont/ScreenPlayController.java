package com.clapp.demo.cont;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clapp.demo.model.ScreenPlay;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;


@RestController
public class ScreenPlayController {
	
		@Autowired
		FirebaseInitializer db;
		@GetMapping("/getAllPhotos")
		public List<ScreenPlay> getAllPhotos() throws InterruptedException, ExecutionException {
			System.out.println("entre a los workers");
			List<ScreenPlay> screenList = new ArrayList<ScreenPlay>();
			CollectionReference screens= db.getFirebase().collection("screens");
			//System.out.println("entre a los productos");
			ApiFuture<QuerySnapshot> querySnapshot= screens.get();
			//System.out.println("entre a los productos");
			for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
				ScreenPlay projecto = doc.toObject(ScreenPlay.class);
				screenList.add(projecto);
			}
			return screenList;
		}
		@PostMapping("/savePhoto")
		public String savePhoto(@RequestBody ScreenPlay screen) {
			DocumentReference addedDocRef = db.getFirebase().collection("screens").document();
			System.out.println("Added project with ID: " + addedDocRef.getId());
			screen.setId(addedDocRef.getId());
			ApiFuture<WriteResult> writeResult = addedDocRef.set(screen);
			System.out.println(writeResult.isDone());
			return screen.getId();
		}
		
		@PutMapping("/updatePhoto/{screenId}")
		public String updatePhoto (@PathVariable String screenId, @RequestBody ScreenPlay screen) {
			
			DocumentReference addedDocRef = db.getFirebase().collection("screens").document(screenId);
			ApiFuture<WriteResult> writeResult = addedDocRef.set(screen);
			System.out.println(writeResult.isDone());
			return screen.getId();
		}
		@DeleteMapping("/deletePhoto/{screenId}")
		public String deletePhoto (@PathVariable String screenId ) {
			
			DocumentReference addedDocRef = db.getFirebase().collection("screens").document(screenId);
			ApiFuture<WriteResult> writeResult = addedDocRef.delete();
			System.out.println("resultado del borrado: "+writeResult.isDone());
			return screenId;
		}
		
		
		@GetMapping("/getPhoto/{screenId}")
		public ScreenPlay getPhoto (@PathVariable String screenId) {
			
			DocumentReference addedDocRef = db.getFirebase().collection("screens").document(screenId);
			ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
			try {
				DocumentSnapshot document = writeResult.get();
				ScreenPlay phot = document.toObject(ScreenPlay.class);
				return phot;
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}


