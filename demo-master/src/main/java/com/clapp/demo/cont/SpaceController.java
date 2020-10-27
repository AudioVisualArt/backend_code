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

import com.clapp.demo.model.Space;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class SpaceController {

	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllSpaces")
	public List<Space> getAllSpaces() throws InterruptedException, ExecutionException {
		System.out.println("entre a los workers");
		List<Space> spaceList = new ArrayList<Space>();
		CollectionReference spaces= db.getFirebase().collection("spaces");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= spaces.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Space projecto = doc.toObject(Space.class);
			spaceList.add(projecto);
		}
		return spaceList;
	}
	@PostMapping("/saveSpace")
	public String saveSpace(@RequestBody Space space) {
		DocumentReference addedDocRef = db.getFirebase().collection("spaces").document();
		System.out.println("Added project with ID: " + addedDocRef.getId());
		space.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(space);
		System.out.println(writeResult.isDone());
		return space.getId();
	}
	
	@PutMapping("/updateSpace/{spaceId}")
	public String updateSpace (@PathVariable String spaceId, @RequestBody Space space) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("spaces").document(spaceId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(space);
		System.out.println(writeResult.isDone());
		return space.getId();
	}
	@DeleteMapping("/deleteSpace/{spaceId}")
	public String deleteSpace (@PathVariable String spaceId ) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("spaces").document(spaceId);
		ApiFuture<WriteResult> writeResult = addedDocRef.delete();
		System.out.println("resultado del borrado: "+writeResult.isDone());
		return spaceId;
	}
	
	
	@GetMapping("/getSpace/{spaceId}")
	public Space getSpace (@PathVariable String spaceId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("spaces").document(spaceId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Space phot = document.toObject(Space.class);
			return phot;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/getAllSpacesUser/{userId}")
	public List<Space> getAllSpacesUser(@PathVariable String userId) throws InterruptedException, ExecutionException {
		
		List<Space> spaceList = new ArrayList<Space>();
		CollectionReference spaces= db.getFirebase().collection("spaces");
		Query query = spaces.whereEqualTo("userOwner", userId);
		
		ApiFuture<QuerySnapshot> querySnapshot= query.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Space space = doc.toObject(Space.class);
			spaceList.add(space);
		}
		return spaceList;
}
}
