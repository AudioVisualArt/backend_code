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

import com.clapp.demo.model.Event;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class EventController {
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllEvents")
	public List<Event> getAllEvents() throws InterruptedException, ExecutionException {
		System.out.println("entre a los productos");
		List<Event> EventList = new ArrayList<Event>();
		CollectionReference Event= db.getFirebase().collection("Events");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= Event.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Event usr = doc.toObject(Event.class);
			EventList.add(usr);
		}
		return EventList;
	}
	
	@PostMapping("/saveEvent")
	public String saveEvent(@RequestBody Event Event) {
		DocumentReference addedDocRef = db.getFirebase().collection("Events").document();
		System.out.println("Added Event with ID: " + addedDocRef.getId());
		Event.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(Event);
		System.out.println(writeResult.isDone());
		return Event.getId();
	}
	@PutMapping("/updateEvent/{EventId}")
	public String updateEvent (@PathVariable String EventId, @RequestBody Event Event) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Events").document(EventId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(Event);
		System.out.println(writeResult.isDone());
		return Event.getId();
	}
	@DeleteMapping("/deleteEvent/{EventId}")
	public String deleteEvent (@PathVariable String EventId ) {
	
	DocumentReference addedDocRef = db.getFirebase().collection("Events").document(EventId);
	ApiFuture<WriteResult> writeResult = addedDocRef.delete();
	System.out.println("resultado del borrado: "+writeResult.isDone());
	return EventId;
}
	@GetMapping("/getEvent/{EventId}")
	public Event getEvent (@PathVariable String EventId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Events").document(EventId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Event usr = document.toObject(Event.class);
			return usr;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
