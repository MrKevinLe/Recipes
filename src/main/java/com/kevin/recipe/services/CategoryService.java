package com.kevin.recipe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.recipe.models.Category;
import com.kevin.recipe.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository CategoryRepo;
//	Find all
	public List<Category>allCategory(){
		return CategoryRepo.findAll();
	}
//	Create
	public Category createCategory(Category N) {
		return CategoryRepo.save(N);
	}
//	FIND BY ID
	public Category findCategory(Long id) {
		Optional<Category> option = CategoryRepo.findById(id);
		if(option.isPresent()) {
			return option.get();
		}else {
			return null;
		}
	}
//	DELTE 
	public void deleteCategory(Long id) {
		CategoryRepo.deleteById(id);
	}

}
