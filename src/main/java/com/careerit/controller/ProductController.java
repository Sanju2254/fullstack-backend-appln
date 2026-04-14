package com.careerit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.careerit.entity.Product;
import com.careerit.service.ProductService;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return service.addProduct(product);
	}
	
	@GetMapping("/products/{category}")
	public List<Product> getAllProductsByCategpry(@PathVariable String category){
		return service.getByCategory(category);
	}
	
	@PostMapping("/products/upload")
	public Product addProductWithImage(
	        @RequestParam("pid") String pid,
	        @RequestParam("pname") String pname,
	        @RequestParam("pcost") double pcost,
	        @RequestParam("pqty") int pqty,
	        @RequestParam("category") String category,
	        @RequestParam("file") MultipartFile file
	) throws Exception {

	    Product product = new Product();
	    product.setPid(pid);
	    product.setPname(pname);
	    product.setPcost(pcost);
	    product.setPqty(pqty);
	    product.setCategory(category);

	    return service.addProductWithImage(product, file);
	}
	
	
}
