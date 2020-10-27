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

import com.clapp.demo.model.Equipment;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class EquipmentController {

	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllEquipments")
	public List<Equipment> getAllEquipments() throws InterruptedException, ExecutionException {
		System.out.println("entre a los productos");
		List<Equipment> EquipmentList = new ArrayList<Equipment>();
		CollectionReference Equipment= db.getFirebase().collection("Equipments");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= Equipment.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Equipment usr = doc.toObject(Equipment.class);
			EquipmentList.add(usr);
		}
		return EquipmentList;
	}
	
	@PostMapping("/saveEquipment")
	public String saveEquipment(@RequestBody Equipment Equipment) {
		DocumentReference addedDocRef = db.getFirebase().collection("Equipments").document();
		System.out.println("Added Equipment with ID: " + addedDocRef.getId());
		Equipment.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(Equipment);
		System.out.println(writeResult.isDone());
		return Equipment.getId();
	}
	@PutMapping("/updateEquipment/{EquipmentId}")
	public String updateEquipment (@PathVariable String EquipmentId, @RequestBody Equipment Equipment) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Equipments").document(EquipmentId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(Equipment);
		System.out.println(writeResult.isDone());
		return Equipment.getId();
	}
	@DeleteMapping("/deleteEquipment/{EquipmentId}")
	public String deleteEquipment (@PathVariable String EquipmentId ) {
	
	DocumentReference addedDocRef = db.getFirebase().collection("Equipments").document(EquipmentId);
	ApiFuture<WriteResult> writeResult = addedDocRef.delete();
	System.out.println("resultado del borrado: "+writeResult.isDone());
	return EquipmentId;
}
	@GetMapping("/getEquipment/{EquipmentId}")
	public Equipment getEquipment (@PathVariable String EquipmentId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Equipments").document(EquipmentId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Equipment usr = document.toObject(Equipment.class);
			return usr;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
