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

import com.cyber.entity.Category;
import com.cyber.exception.CommonServiceException;
import com.cyber.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<String> addCategory(@RequestBody Category category)
	{
		try {
		  String msg = categoryService.isCategoryPresent(category);
		  return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		}
		catch(CommonServiceException e)
		{
	        return new ResponseEntity<>("Category is already exists...!!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id)
	{
		try {
		Category category = categoryService.getcategoryById(id);
		return new ResponseEntity<Category>(category,HttpStatus.OK);
		}
		catch(CommonServiceException e)
		{
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCategoryById(@PathVariable int id, @RequestBody Category category)
	{
		try {
		String msg = categoryService.updateCategoryById(id, category);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(CommonServiceException e)
		{
	        return new ResponseEntity<>("Sorry, The Category you are looking for is not avaliable...!!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable int id)
	{
		try {
		String msg = categoryService.deleteCategoryById(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
		}
		catch(CommonServiceException e)
		{
	        return new ResponseEntity<>("Sorry, The Category you are looking for is not avaliable...!!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> getDataByPagination(  @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
	{
		Page<Category> categories = categoryService.getAllCategories(page, size);
            List<Category> category=categories.getContent();
                      
		return new ResponseEntity<List<Category>>(category,HttpStatus.OK);
	}
}
