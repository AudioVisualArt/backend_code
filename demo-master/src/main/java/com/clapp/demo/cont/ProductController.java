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

import com.clapp.demo.model.Product;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;


@RestController
public class ProductController {

	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() throws InterruptedException, ExecutionException {
		System.out.println("entre a los productos");
		List<Product> prodList = new ArrayList<Product>();
		CollectionReference product= db.getFirebase().collection("products");
		//System.out.println("entre a los productos");
		ApiFuture<QuerySnapshot> querySnapshot= product.get();
		//System.out.println("entre a los productos");
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Product pro = doc.toObject(Product.class);
			prodList.add(pro);
		}
		return prodList;
	}
	@PostMapping("/saveProduct")
	public String saveProduct(@RequestBody Product product) {
		DocumentReference addedDocRef = db.getFirebase().collection("products").document();
		System.out.println("Added document with ID: " + addedDocRef.getId());
		product.setId(addedDocRef.getId());
		ApiFuture<WriteResult> writeResult = addedDocRef.set(product);
		System.out.println(writeResult.isDone());
		return product.getId();
	}
	
	@PutMapping("/updateProduct/{prodId}")
	public String updateProduct (@PathVariable String prodId, @RequestBody Product product) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("products").document(prodId);
		ApiFuture<WriteResult> writeResult = addedDocRef.set(product);
		System.out.println(writeResult.isDone());
		return product.getId();
	}
	@DeleteMapping("/deleteProduct/{prodId}")
		public String deleteProduct (@PathVariable String prodId ) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("products").document(prodId);
		ApiFuture<WriteResult> writeResult = addedDocRef.delete();
		System.out.println("resultado del borrado: "+writeResult.isDone());
		return prodId;
	}
	
	@GetMapping("/getProduct/{prodId}")
	public Product getProduct (@PathVariable String prodId) {
		
		DocumentReference addedDocRef = db.getFirebase().collection("products").document(prodId);
		ApiFuture<DocumentSnapshot> writeResult = addedDocRef.get();
		try {
			DocumentSnapshot document = writeResult.get();
			Product pro = document.toObject(Product.class);
			return pro;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
