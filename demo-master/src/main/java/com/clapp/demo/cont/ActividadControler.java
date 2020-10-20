package com.clapp.demo.cont;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clapp.demo.model.Actividad;
import com.clapp.demo.model.Chat;
import com.clapp.demo.model.Mensaje;
import com.clapp.demo.model.Registro;
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
public class ActividadControler {
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllActivities/{userId}")
	public List<Actividad> getAllActivities(@PathVariable String userId) throws InterruptedException, ExecutionException {
		
		List<Actividad> activityList = new ArrayList<Actividad>();
		CollectionReference registro= db.getFirebase().collection("registros ");
		Query query = registro.whereEqualTo("IdUser", userId);
		ApiFuture<QuerySnapshot> querySnapshot= query.get();
		//System.out.println("entre a los productos");
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			System.out.println("el id del registro"+doc.getId());
			CollectionReference activities=db.getFirebase().collection("registros ").document(doc.getId()).collection("actividades");
			ApiFuture<QuerySnapshot> querySnapshot2= activities.get();
			for(DocumentSnapshot doc2:querySnapshot2.get().getDocuments()) {
				Actividad act=doc2.toObject(Actividad.class);
				activityList.add(act);
			}
		}
		
		return activityList;
	}
	@PostMapping("/saveActivity/{userId}")
	public String saveActivity(@RequestBody Actividad activity,@PathVariable String userId) {
		CollectionReference registro= db.getFirebase().collection("registros ");
		Query query = registro.whereEqualTo("IdUser", userId);
		ApiFuture<QuerySnapshot> querySnapshot= query.get();
		try {
			if(querySnapshot.get().getDocuments().size()==0) {
				crearRegistro(userId);
				querySnapshot= query.get();
			}
			
			DocumentReference addedDocRef = db.getFirebase().collection("registros ").document(querySnapshot.get().getDocuments().get(0).getId());
			ApiFuture<DocumentReference> writeResult =addedDocRef.collection("actividades").add(activity);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "";
	}
	public void crearRegistro(String userId) {
		Registro r=new Registro();
		r.setIdUser(userId);
		DocumentReference addedDocRef = db.getFirebase().collection("registros ").document();	
		ApiFuture<WriteResult> writeResult = addedDocRef.set(r);
	
	}
}
