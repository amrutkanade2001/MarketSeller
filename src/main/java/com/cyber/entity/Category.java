package com.cyber.entity;

import java.time.LocalDate;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category_tbl")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	private String category_name;
	private String category_description;
	    private LocalDate category_created_at;
	    private LocalDate category_updated_at;

	  @JsonManagedReference
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Product> product;

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	

	
	public List<Product> getProduct() {
		return product;
	}

	

	public LocalDate getCategory_created_at() {
		return category_created_at;
	}

	public void setCategory_created_at(LocalDate category_created_at) {
		this.category_created_at = category_created_at;
	}

	public LocalDate getCategory_updated_at() {
		return category_updated_at;
	}

	public void setCategory_updated_at(LocalDate category_updated_at) {
		this.category_updated_at = category_updated_at;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}


	
	
	
}
