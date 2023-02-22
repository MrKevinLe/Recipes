package com.kevin.recipe.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevin.recipe.models.Recipe;
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	List<Recipe>findAll();
}
