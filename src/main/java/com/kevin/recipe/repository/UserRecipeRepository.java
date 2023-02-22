package com.kevin.recipe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevin.recipe.models.UserRecipe;
@Repository
public interface UserRecipeRepository extends CrudRepository<UserRecipe, Long> {
	List<UserRecipe>findAll();

}
