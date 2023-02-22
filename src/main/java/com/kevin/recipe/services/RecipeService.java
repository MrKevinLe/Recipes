package com.kevin.recipe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.recipe.models.Recipe;
import com.kevin.recipe.repository.RecipeRepository;

@Service
public class RecipeService {
	@Autowired
	private RecipeRepository recipeRepo;
	
//	Find all
	public List<Recipe>allRecipe(){
		return recipeRepo.findAll();
	}
//	Create
	public Recipe createRecipe(Recipe N) {
		return recipeRepo.save(N);
	}
//	FIND BY ID
	public Recipe findRecipe(Long id) {
		Optional<Recipe> option = recipeRepo.findById(id);
		if(option.isPresent()) {
			return option.get();
		}else {
			return null;
		}
	}
//	DELTE 
	public void deleteRecipe(Long id) {
		recipeRepo.deleteById(id);
	}
////	Update
//	public Baby updateBaby(Long id,String name, String gender, String origin, String meaning) {
//		Baby baby = babyRepo.findById(id).get();
//		baby.setName(name);
//		baby.setGender(gender);
//		baby.setOrigin(origin);
//		baby.setMeaning(meaning);
//		return babyRepo.save(baby);
//				
//	}
	public Recipe updateRecipe(Recipe r) {
		Recipe updatedRecipe = recipeRepo.save(r);
		return updatedRecipe;
	}
	

}
