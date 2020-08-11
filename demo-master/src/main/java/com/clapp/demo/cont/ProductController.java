package com.clapp.demo.cont;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clapp.demo.model.Employee;
import com.clapp.demo.model.Product;
import com.clapp.demo.service.FirebaseInitializer;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@RestController
public class ProductController {

	@Autowired
	FirebaseInitializer db;
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts() throws InterruptedException, ExecutionException {
		List<Product> prodList = new ArrayList<Product>();
		CollectionReference product= db.getFirebase().collection("products");
		ApiFuture<QuerySnapshot> querySnapshot= product.get();
		for(DocumentSnapshot doc:querySnapshot.get().getDocuments()) {
			Product pro = doc.toObject(Product.class);
			prodList.add(pro);
		}
		return prodList;
	}
	
	
	
}
