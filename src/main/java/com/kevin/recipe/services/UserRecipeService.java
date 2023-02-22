package com.kevin.recipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.recipe.models.UserRecipe;
import com.kevin.recipe.repository.UserRecipeRepository;

@Service
public class UserRecipeService {
	@Autowired
	private UserRecipeRepository URserv;
//	Find all
	public List<UserRecipe>allLikedRecipe(){
		return URserv.findAll();
	}
	
	

}
