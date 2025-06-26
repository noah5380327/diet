package org.app.diet.service;

import org.app.diet.entity.RecipeEntity;

public interface RecipeService {

   RecipeEntity generateRecipe();

   RecipeEntity getLatestRecipe();

   RecipeEntity getRecipeByStudentId(String studentId);
}
