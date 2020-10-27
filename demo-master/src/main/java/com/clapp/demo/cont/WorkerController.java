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

import com.clapp.demo.model.Project;
import com.clapp.demo.model.Worker;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class WorkerController {
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllWorkers")
	public List<Worker> getAllWorkers() throws InterruptedException, ExecutionException {
		System.out.println("entre a los workers");
		List<Worker> workerList = new ArrayList<Worker>();
		CollectionReference workers= db.getFirebase().collection("workers");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= workers.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Worker projecto = doc.toObject(Worker.class);
			workerList.add(projecto);
		}
		return workerList;
	}
	@PostMapping("/saveWorker")
	public String saveProject(@RequestBody Worker work) {
		DocumentReference addedDocRef = db.getFirebase().collection("workers").document();
		System.out.println("Added project with ID: " + addedDocRef.getId());
		//work.setUserId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(work);
		System.out.println(writeResult.isDone());
		return work.getUserId();
	}
	
	@PutMapping("/updateWorker/{workerId}")
	public String updateProject (@PathVariable String workerId, @RequestBody Worker work) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("workers").document(workerId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(work);
		System.out.println(writeResult.isDone());
		return work.getUserId();
	}
	@DeleteMapping("/deleteWorker/{workerId}")
	public String deleteProject (@PathVariable String workerId ) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("workers").document(workerId);
		ApiFuture<WriteResult> writeResult = addedDocRef.delete();
		System.out.println("resultado del borrado: "+writeResult.isDone());
		return workerId;
	}
	
	
	@GetMapping("/getWorker/{workerId}")
	public Worker getProduct (@PathVariable String workerId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("workers").document(workerId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Worker work = document.toObject(Worker.class);
			return work;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getWorkerUserId/{userId}")
	public Worker getWorkerUserId (@PathVariable String userId) {
		System.out.println("busco al worker con id"+userId);
		CollectionReference addedDocRef = db.getFirebase().collection("workers");
		Query query = addedDocRef.whereEqualTo("userId", userId);
		ApiFuture<QuerySnapshot> writeResult = query.get();
		try {
			DocumentSnapshot document = writeResult.get().getDocuments().get(0);
			Worker work = document.toObject(Worker.class);
			return work;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getAllWorkersFromUser/{userId}")
	public List<Worker> getAllWorkersFromUser(@PathVariable String userId) throws InterruptedException, ExecutionException {
		
		List<Worker> workerList = new ArrayList<Worker>();
		CollectionReference workers= db.getFirebase().collection("workers");
		Query query = workers.whereEqualTo("userId", userId);
		ApiFuture<QuerySnapshot> querySnapshot= query.get();
		
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Worker projecto = doc.toObject(Worker.class);
			workerList.add(projecto);
		}
		return workerList;
	}
}
