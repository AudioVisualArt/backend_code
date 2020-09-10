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

import com.clapp.demo.model.Music;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class MusicController {
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllMusics")
	public List<Music> getAllMusics() throws InterruptedException, ExecutionException {
		System.out.println("entre a los workers");
		List<Music> musicList = new ArrayList<Music>();
		CollectionReference musics= db.getFirebase().collection("musics");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= musics.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Music projecto = doc.toObject(Music.class);
			musicList.add(projecto);
		}
		return musicList;
	}
	@PostMapping("/saveMusic")
	public String saveMusic(@RequestBody Music music) {
		DocumentReference addedDocRef = db.getFirebase().collection("musics").document();
		System.out.println("Added project with ID: " + addedDocRef.getId());
		music.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(music);
		System.out.println(writeResult.isDone());
		return music.getId();
	}
	
	@PutMapping("/updateMusic/{musicId}")
	public String updateMusic (@PathVariable String musicId, @RequestBody Music music) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("musics").document(musicId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(music);
		System.out.println(writeResult.isDone());
		return music.getId();
	}
	@DeleteMapping("/deleteMusic/{musicId}")
	public String deleteMusic (@PathVariable String musicId ) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("musics").document(musicId);
		ApiFuture<WriteResult> writeResult = addedDocRef.delete();
		System.out.println("resultado del borrado: "+writeResult.isDone());
		return musicId;
	}
	
	
	@GetMapping("/getMusic/{musicId}")
	public Music getMusic (@PathVariable String musicId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("musics").document(musicId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Music phot = document.toObject(Music.class);
			return phot;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
