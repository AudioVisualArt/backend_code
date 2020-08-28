package com.clapp.demo.cont;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clapp.demo.model.Item;
import com.clapp.demo.model.Project;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class ProjectController {
	
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllProjects")
	public List<Project> getAllProjects() throws InterruptedException, ExecutionException {
		System.out.println("entre a los proyectos");
		List<Project> projectList = new ArrayList<Project>();
		CollectionReference project= db.getFirebase().collection("projects");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= project.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Project projecto = doc.toObject(Project.class);
			projectList.add(projecto);
		}
		return projectList;
	}
	@PostMapping("/saveProject")
	public String saveProject(@RequestBody Project proj) {
		DocumentReference addedDocRef = db.getFirebase().collection("projects").document();
		System.out.println("Added project with ID: " + addedDocRef.getId());
		proj.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(proj);
		System.out.println(writeResult.isDone());
		return proj.getId();
	}
	
	@PutMapping("/updateProject/{projectId}")
	public String updateProject (@PathVariable String projectId, @RequestBody Project proj) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("projects").document(projectId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(proj);
		System.out.println(writeResult.isDone());
		return proj.getId();
	}
	@DeleteMapping("/deleteProject/{projectId}")
	public String deleteProject (@PathVariable String projectId ) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("projects").document(projectId);
		ApiFuture<WriteResult> writeResult = addedDocRef.delete();
		System.out.println("resultado del borrado: "+writeResult.isDone());
		return projectId;
	}
	
	
	@GetMapping("/getProject/{projectId}")
	public Project getProduct (@PathVariable String projectId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("projects").document(projectId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Project pro = document.toObject(Project.class);
			return pro;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
