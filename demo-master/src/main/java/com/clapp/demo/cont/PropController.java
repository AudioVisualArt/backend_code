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
import com.clapp.demo.model.Prop;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class PropController {
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllProps")
	public List<Prop> getAllProps() throws InterruptedException, ExecutionException {
		System.out.println("entre a los workers");
		List<Prop> propList = new ArrayList<Prop>();
		CollectionReference props= db.getFirebase().collection("props");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= props.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Prop projecto = doc.toObject(Prop.class);
			propList.add(projecto);
		}
		return propList;
	}
	@PostMapping("/saveProp")
	public String saveProp(@RequestBody Prop prop) {
		DocumentReference addedDocRef = db.getFirebase().collection("props").document();
		System.out.println("Added project with ID: " + addedDocRef.getId());
		prop.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(prop);
		System.out.println(writeResult.isDone());
		return prop.getId();
	}
	
	@PutMapping("/updateProp/{propId}")
	public String updateProp (@PathVariable String propId, @RequestBody Prop prop) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("props").document(propId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(prop);
		System.out.println(writeResult.isDone());
		return prop.getId();
	}
	@DeleteMapping("/deleteProp/{propId}")
	public String deleteProp (@PathVariable String propId ) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("props").document(propId);
		ApiFuture<WriteResult> writeResult = addedDocRef.delete();
		System.out.println("resultado del borrado: "+writeResult.isDone());
		return propId;
	}
	
	
	@GetMapping("/getProp/{propId}")
	public Prop getProp (@PathVariable String propId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("props").document(propId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Prop phot = document.toObject(Prop.class);
			return phot;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
