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

import com.clapp.demo.model.Item;
import com.clapp.demo.model.User;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class UserController {

	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() throws InterruptedException, ExecutionException {
		System.out.println("entre a los productos");
		List<User> userList = new ArrayList<User>();
		CollectionReference user= db.getFirebase().collection("users");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= user.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			User usr = doc.toObject(User.class);
			userList.add(usr);
		}
		return userList;
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@RequestBody User user) {
		DocumentReference addedDocRef = db.getFirebase().collection("users").document();
		System.out.println("Added user with ID: " + addedDocRef.getId());
		user.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(user);
		System.out.println(writeResult.isDone());
		return user.getId();
	}
	@PutMapping("/updateUser/{userId}")
	public String updateUser (@PathVariable String userId, @RequestBody User user) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("users").document(userId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(user);
		System.out.println(writeResult.isDone());
		return user.getId();
	}
	@DeleteMapping("/deleteUser/{userId}")
	public String deleteUser (@PathVariable String userId ) {
	
	DocumentReference addedDocRef = db.getFirebase().collection("users").document(userId);
	ApiFuture<WriteResult> writeResult = addedDocRef.delete();
	System.out.println("resultado del borrado: "+writeResult.isDone());
	return userId;
}
	@GetMapping("/getUser/{userId}")
	public User getUser (@PathVariable String userId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("users").document(userId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			User usr = document.toObject(User.class);
			return usr;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getUserByEmail/{email}")
	public User getUserByEmail (@PathVariable String email) {
		
		CollectionReference addedDocRef = db.getFirebase().collection("users");
		Query query = addedDocRef.whereEqualTo("email", email);
		ApiFuture<QuerySnapshot> writeResult = addedDocRef.get();
		try {
			for (DocumentSnapshot document : writeResult.get().getDocuments()) {
				  System.out.println(document.getId());
				  User usr = document.toObject(User.class);
				  return usr;
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
