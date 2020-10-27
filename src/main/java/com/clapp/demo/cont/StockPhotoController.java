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

import com.clapp.demo.model.StockPhoto;
import com.clapp.demo.model.Worker;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class StockPhotoController {
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllPhotos")
	public List<StockPhoto> getAllPhotos() throws InterruptedException, ExecutionException {
		System.out.println("entre a los workers");
		List<StockPhoto> photoList = new ArrayList<StockPhoto>();
		CollectionReference photos= db.getFirebase().collection("photos");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= photos.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			StockPhoto projecto = doc.toObject(StockPhoto.class);
			photoList.add(projecto);
		}
		return photoList;
	}
	@PostMapping("/savePhoto")
	public String savePhoto(@RequestBody StockPhoto photo) {
		DocumentReference addedDocRef = db.getFirebase().collection("photos").document();
		System.out.println("Added project with ID: " + addedDocRef.getId());
		photo.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(photo);
		System.out.println(writeResult.isDone());
		return photo.getId();
	}
	
	@PutMapping("/updatePhoto/{photoId}")
	public String updatePhoto (@PathVariable String photoId, @RequestBody StockPhoto photo) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("photos").document(photoId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(photo);
		System.out.println(writeResult.isDone());
		return photo.getId();
	}
	@DeleteMapping("/deletePhoto/{photoId}")
	public String deletePhoto (@PathVariable String photoId ) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("photos").document(photoId);
		ApiFuture<WriteResult> writeResult = addedDocRef.delete();
		System.out.println("resultado del borrado: "+writeResult.isDone());
		return photoId;
	}
	
	
	@GetMapping("/getPhoto/{photoId}")
	public StockPhoto getPhoto (@PathVariable String photoId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("photos").document(photoId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			StockPhoto phot = document.toObject(StockPhoto.class);
			return phot;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
