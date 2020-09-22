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

import com.clapp.demo.model.Contract;
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
public class ContractController {
	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllContracts")
	public List<Contract> getAllContracts() throws InterruptedException, ExecutionException {
		System.out.println("entre a los contractsos");
		List<Contract> contList = new ArrayList<Contract>();
		CollectionReference contracts= db.getFirebase().collection("Contracts");
		//System.out.println("entre a los contractsos");
		ApiFuture<QuerySnapshot> querySnapshot= contracts.get();
		//System.out.println("entre a los contractsos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Contract pro = doc.toObject(Contract.class);
			contList.add(pro);
		}
		return contList;
	}
	@GetMapping("/getAllContractsBidder/{bidderId}")
	public List<Contract> getAllContractsBidder(@PathVariable String bidderId) throws InterruptedException, ExecutionException {
		CollectionReference addedDocRef = db.getFirebase().collection("Contracts");
		Query query = addedDocRef.whereEqualTo("userBidderId", bidderId);
		ApiFuture<QuerySnapshot> writeResult = query.get();
		try {
			List<Contract> contracts = new ArrayList<Contract>();
			for (DocumentSnapshot document : writeResult.get().getDocuments()) {
				  System.out.println(document.getId());
				  contracts.add(document.toObject(Contract.class));
				  }
			return contracts;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/getAllContractsRecibed/{aplicantId}")
	public List<Contract> getAllContractsRecibed(@PathVariable String aplicantId) throws InterruptedException, ExecutionException {
		CollectionReference addedDocRef = db.getFirebase().collection("Contracts");
		Query query = addedDocRef.whereEqualTo("userApplicantId", aplicantId).whereEqualTo("accepted", false);
		ApiFuture<QuerySnapshot> writeResult = query.get();
		try {
			List<Contract> contracts = new ArrayList<Contract>();
			for (DocumentSnapshot document : writeResult.get().getDocuments()) {
				  System.out.println(document.getId());
				  contracts.add(document.toObject(Contract.class));
				  }
			return contracts;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/getAllContractsProject/{projectId}")
	public List<Contract> getAllContractsBidderProject(@PathVariable String projectId) throws InterruptedException, ExecutionException {
		CollectionReference addedDocRef = db.getFirebase().collection("Contracts");
		Query query = addedDocRef.whereEqualTo("projectId", projectId).whereEqualTo("accepted", false).whereEqualTo("userApplicantId", null);
		ApiFuture<QuerySnapshot> writeResult = query.get();
		try {
			List<Contract> contracts = new ArrayList<Contract>();
			for (DocumentSnapshot document : writeResult.get().getDocuments()) {
				  System.out.println(document.getId());
				  contracts.add(document.toObject(Contract.class));
				  }
			return contracts;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/saveContract")
	public String saveContract(@RequestBody Contract contract) {
		DocumentReference addedDocRef = db.getFirebase().collection("Contracts").document();
		System.out.println("Added Contract with ID: " + addedDocRef.getId());
		contract.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(contract);
		System.out.println(writeResult.isDone());
		return contract.getId();
	}
	@PutMapping("/updateContract/{ContractId}")
	public String updateContract (@PathVariable String ContractId, @RequestBody Contract contract) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Contracts").document(ContractId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(contract);
		System.out.println(writeResult.isDone());
		return contract.getId();
	}
	@DeleteMapping("/deleteContract/{ContractId}")
	public String deleteContract (@PathVariable String ContractId ) {
	
	DocumentReference addedDocRef = db.getFirebase().collection("Contracts").document(ContractId);
	ApiFuture<WriteResult> writeResult = addedDocRef.delete();
	System.out.println("resultado del borrado: "+writeResult.isDone());
	return ContractId;
}
	@GetMapping("/getContract/{ContractId}")
	public Contract getContract (@PathVariable String ContractId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Contracts").document(ContractId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Contract con = document.toObject(Contract.class);
			return con;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
