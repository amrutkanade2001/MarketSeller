package com.cyber.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="product_tbl")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	private String product_name;
	private String product_description ;
	private double product_price;
	private int product_quantity;
	
	
	 private LocalDate product_created_at;
	  private LocalDate product_updated_at;
	
	  @JsonBackReference
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="category_id")
	private Category category;


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getProduct_description() {
		return product_description;
	}


	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}


	public double getProduct_price() {
		return product_price;
	}


	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}


	public int getProduct_quantity() {
		return product_quantity;
	}


	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}


	

	public LocalDate getProduct_created_at() {
		return product_created_at;
	}


	public void setProduct_created_at(LocalDate product_created_at) {
		this.product_created_at = product_created_at;
	}


	public LocalDate getProduct_updated_at() {
		return product_updated_at;
	}


	public void setProduct_updated_at(LocalDate product_updated_at) {
		this.product_updated_at = product_updated_at;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
