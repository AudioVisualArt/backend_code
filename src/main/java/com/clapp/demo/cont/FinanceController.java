package com.clapp.demo.cont;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.clapp.demo.model.Finance;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;

@RestController
public class FinanceController {
	@Autowired
	FirebaseInitializer db;
	
	@GetMapping("/getAllFinancesProjects/{projectId}")
	public List<Finance> getAllFinancesProjects(@PathVariable String projectId) throws InterruptedException, ExecutionException {
		System.out.println("entre a los productos");
		List<Finance> FinanceList = new ArrayList<Finance>();
		CollectionReference Finance= db.getFirebase().collection("Finances");
		Query query = Finance.whereEqualTo("projectId", projectId);
		ApiFuture<QuerySnapshot> querySnapshot= query.get();
		//System.out.println("entre a los productos");
		Finance total = new Finance();
		double total_percentage=0.0;
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Finance usr = doc.toObject(Finance.class);
			if(usr.getTitle().equals("Total")) {
				total=usr;
			}
			FinanceList.add(usr);
		}
		for (Finance fin : FinanceList) {
			if(!fin.getTitle().equals("Total")) {
				fin.setPercentage(round(fin.getQuantity()/total.getQuantity(),3));
				total_percentage += fin.getPercentage();
			}
		}
		
		Finance temp = new Finance();
		for (int i = 0 ; i<FinanceList.size();++i) {
			if(FinanceList.get(i).getTitle().equals("Total")) {
				FinanceList.get(i).setPercentage(total_percentage);
				temp = FinanceList.get(i);
				FinanceList.remove(i);		
			}
		}
		FinanceList.add(temp);
		
		return FinanceList;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	@GetMapping("/getAllFinances")
	public List<Finance> getAllFinances() throws InterruptedException, ExecutionException {
		System.out.println("entre a los productos");
		List<Finance> FinanceList = new ArrayList<Finance>();
		CollectionReference Finance= db.getFirebase().collection("Finances");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= Finance.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Finance usr = doc.toObject(Finance.class);
			FinanceList.add(usr);
		}
		return FinanceList;
	}
	
	@PostMapping("/saveFinance")
	public String saveFinance(@RequestBody Finance Finance) {
		DocumentReference addedDocRef = db.getFirebase().collection("Finances").document();
		System.out.println("Added Finance with ID: " + addedDocRef.getId());
		Finance.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(Finance);
		System.out.println(writeResult.isDone());
		return Finance.getId();
	}
	@PutMapping("/updateFinance/{FinanceId}")
	public String updateFinance (@PathVariable String FinanceId, @RequestBody Finance Finance) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Finances").document(FinanceId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(Finance);
		System.out.println(writeResult.isDone());
		return Finance.getId();
	}
	@DeleteMapping("/deleteFinance/{FinanceId}")
	public String deleteFinance (@PathVariable String FinanceId ) {
	
	DocumentReference addedDocRef = db.getFirebase().collection("Finances").document(FinanceId);
	ApiFuture<WriteResult> writeResult = addedDocRef.delete();
	System.out.println("resultado del borrado: "+writeResult.isDone());
	return FinanceId;
}
	@GetMapping("/getFinance/{FinanceId}")
	public Finance getFinance (@PathVariable String FinanceId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("Finances").document(FinanceId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Finance usr = document.toObject(Finance.class);
			return usr;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
