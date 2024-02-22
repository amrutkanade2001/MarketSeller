package com.cyber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyber.entity.Product;
import com.cyber.exception.CommonServiceException;
import com.cyber.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<String> addProduct(@RequestBody Product product)
	{
		
		  try { 
			  String msg = productService.isProductPresent(product); 
			  return new ResponseEntity<String>(msg,HttpStatus.CREATED);
			  }
		  catch(CommonServiceException e) 
		  { 
		  return new  ResponseEntity<>("Product is already exists...!!", HttpStatus.BAD_REQUEST); 
		  }
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id)
	{
		try {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
		}
		catch(CommonServiceException e)
		{
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateProductById(@PathVariable int id, @RequestBody Product product)
	{
		try {
		String msg = productService.updateProductById(id, product);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(CommonServiceException e)
		{
	        return new ResponseEntity<>("Sorry, The Product you are looking for is not avaliable...!!",HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable int id)
	{
		try {
		String msg = productService.deleteProductById(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(CommonServiceException e)
		{
	        return new ResponseEntity<>("Sorry, The Product you are looking for is not avaliable...!!",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getDataByPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size)
	{
		Page<Product> products = productService.getAllProducts(page, size);
		List<Product> content = products.getContent();
		
		return new ResponseEntity<List<Product>>(content,HttpStatus.OK);
	}
	
	
	
}
