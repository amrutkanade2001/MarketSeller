package com.cyber.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cyber.entity.Product;
import com.cyber.exception.CommonServiceException;
import com.cyber.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;


	public String isProductPresent(Product product) {
		
		if(productRepository.isProductPresent(product.getProduct_name()) == 1)
			throw new CommonServiceException("Product is already exists...!!");
		else
		{
			LocalDate now = LocalDate.now();
			product.setProduct_created_at(now);
			product.setProduct_updated_at(now);

			productRepository.save(product);
		return "Product has been created...!!";
		}
	}
	
	public Product getProductById(int id) {
		
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new CommonServiceException("Sorry, The Product you are looking for is not avaliable...!!")
);
		return product;
	
	}
	
	public String updateProductById(int id, Product product) {

		Product productDB = productRepository.findById(id)
				.orElseThrow(() -> new CommonServiceException("Sorry, The Product you are looking for is not avaliable...!!")
);	
		productDB.setProduct_price(product.getProduct_price());
		productDB.setProduct_quantity(product.getProduct_quantity());
		productDB.setProduct_updated_at(LocalDate.now());
		productRepository.save(productDB);
		
		return "Product has been updated Successfully";
	
	}

	
	public String deleteProductById(int id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new CommonServiceException("Sorry, The Product you are looking for is not avaliable...!!")
);
		productRepository.delete(product);
		return "Product has been deleted Successfully";

	}

	public Page<Product> getAllProducts(int page,int size) {
		
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable);

	}
	
	
}
