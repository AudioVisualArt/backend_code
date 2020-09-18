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

import com.clapp.demo.model.Chat;
import com.clapp.demo.model.Mensaje;
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
public class ChatController {
	@Autowired
	FirebaseInitializer db;
	
	@PostMapping("/saveMessage/{fecha}")
	public String saveMessage(@RequestBody Mensaje msg,@PathVariable String fecha) {
	
		System.out.println("print "+msg.getContenido());
		DocumentReference addedDocRef1=db.getFirebase().collection("chats").document(msg.getChatid());
		DocumentReference addedDocRef = db.getFirebase().collection("chats").document(msg.getChatid()).collection("mensajes").document();
		ApiFuture<WriteResult> writeResult = addedDocRef.set(msg);
		ApiFuture<DocumentSnapshot> writeResult2 = addedDocRef1.get();
		try {
			
			DocumentSnapshot document = writeResult2.get();
			Chat cht = document.toObject(Chat.class);
			cht.setFecha(fecha);
			ApiFuture<WriteResult> writeChat = addedDocRef1.set(cht);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Added msg with ID: " + addedDocRef.getId());
		System.out.println(writeResult.isDone());
		return "ok";
	}
	@PostMapping("/saveChat")
	public String saveChat(@RequestBody Chat chat) {
		DocumentReference addedDocRef = db.getFirebase().collection("chats").document();
		System.out.println("Added chat with ID: " + addedDocRef.getId());
		chat.setChatId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(chat);
		DocumentReference addedDocRef2 = db.getFirebase().collection("chats").document(chat.getChatId());
		
		//DocumentReference addedDocRef2 = db.getFirebase().collection("chats").document(chat.getChatId());
		//ApiFuture<QuerySnapshot> writeResult2 = addedDocRef2.collection("mensajes").get();
		System.out.println(writeResult.isDone());
		return chat.getChatId();
	}
	
	@GetMapping("/getAllChats/{userId}")
	public List<Chat> getAllChats(@PathVariable String userId) throws InterruptedException, ExecutionException {
		System.out.println("entre a los chats");
		List<Chat> chatList = new ArrayList<Chat>();
		List<Mensaje> mensajes=new ArrayList<Mensaje>();
		CollectionReference chat= db.getFirebase().collection("chats");
		Query query = chat.whereEqualTo("usuarioD", userId);
		Query query2 = chat.whereEqualTo("usuarioO", userId);
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= query.get();
		ApiFuture<QuerySnapshot> querySnapshotb= query2.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Chat cht = doc.toObject(Chat.class);
			CollectionReference msgs=db.getFirebase().collection("chats").document(cht.getChatId()).collection("mensajes");
			ApiFuture<QuerySnapshot> querySnapshot2= msgs.get();
			for(DocumentSnapshot doc2:querySnapshot2.get().getDocuments()) {
				Mensaje msg=doc2.toObject(Mensaje.class);
				mensajes.add(msg);
			}
			cht.setMensajes(mensajes);
			chatList.add(cht);
		}
		for(DocumentSnapshot doc:querySnapshotb.get().getDocuments()) {
			Chat cht = doc.toObject(Chat.class);
			CollectionReference msgs=db.getFirebase().collection("chats").document(cht.getChatId()).collection("mensajes");
			ApiFuture<QuerySnapshot> querySnapshot2= msgs.get();
			for(DocumentSnapshot doc2:querySnapshot2.get().getDocuments()) {
				Mensaje msg=doc2.toObject(Mensaje.class);
				mensajes.add(msg);
			}
			cht.setMensajes(mensajes);
			chatList.add(cht);
		}
		return chatList;
	}
}
