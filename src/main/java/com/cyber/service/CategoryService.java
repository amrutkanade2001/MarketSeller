package com.cyber.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.cyber.entity.Category;
import com.cyber.entity.Product;
import com.cyber.exception.CommonServiceException;
import com.cyber.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public String isCategoryPresent(Category category) {
	
		if(categoryRepository.isCategoryPresent(category.getCategory_name()) == 1)
			throw new CommonServiceException("Category is already exists...!!");
		else
		{
			LocalDate now=LocalDate.now();
			category.setCategory_created_at(now);
			category.setCategory_updated_at(now);
			List<Product> products = category.getProduct();
			for(Product prod:products)
			{
				prod.setProduct_created_at(now);
				prod.setProduct_updated_at(now);
				prod.setCategory(category);
			}
			category.setProduct(products);
			
			categoryRepository.save(category);
			
		return "Category has been created...!!";
		}
	}
	
	public Category getcategoryById(int id) {
		
			Category category = categoryRepository.findById(id)
					.orElseThrow(() -> new CommonServiceException("Sorry, The Category you are looking for is not avaliable...!!")
);
			return category;
		
	}
	
	public String updateCategoryById(int id,Category category)
	{
		Category categoryDB = categoryRepository.findById(id)
				.orElseThrow(() -> new CommonServiceException("Sorry, The Category you are looking for is not avaliable...!!")
);
		
		categoryDB.setCategory_updated_at(LocalDate.now());
		List<Product> products = category.getProduct();
		LocalDate now=LocalDate.now();
		for(Product prod:products)
		{
			prod.setProduct_created_at(now);
			prod.setProduct_updated_at(now);
			prod.setCategory(categoryDB);
		}
		categoryDB.setProduct(products);
		
		categoryRepository.save(categoryDB);
		return "Category has been updated Successfully";
	}
	
	public String deleteCategoryById(int id)
	{
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CommonServiceException("Sorry, The Category you are looking for is not avaliable...!!")
);
		categoryRepository.delete(category);
		
		return "Category has been deleted Successfully";
	}
	
	
	public Page<Category> getAllCategories(int page,int size) 
	{
		 Pageable pageable = PageRequest.of(page, size);
	    return categoryRepository.findAll(pageable);
	}

}
