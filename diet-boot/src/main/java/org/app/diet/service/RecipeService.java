package org.app.diet.service;

import org.app.diet.entity.RecipeEntity;
import org.app.diet.vo.CoachStudentUpdateVo;

public interface RecipeService {

   RecipeEntity generateRecipe();

   RecipeEntity getLatestRecipe();
}
