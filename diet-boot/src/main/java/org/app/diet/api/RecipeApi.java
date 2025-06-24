package org.app.diet.api;

import org.app.diet.annotation.CoreApi;
import org.app.diet.dto.ApiDto;
import org.app.diet.entity.RecipeEntity;
import org.app.diet.service.RecipeService;
import org.app.diet.util.ApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CoreApi
public class RecipeApi {

    @Autowired
    private RecipeService recipeService;

 @PostMapping("/recipes/generate")
    public ApiDto generateRecipe() {
        RecipeEntity recipeEntity = recipeService.generateRecipe();
        return ApiUtil.success(recipeEntity);
    }

    @GetMapping("/recipes/latest")
    public ApiDto getLatestRecipe() {
        RecipeEntity recipeEntity = recipeService.getLatestRecipe();
        return ApiUtil.success(recipeEntity);
    }
}
