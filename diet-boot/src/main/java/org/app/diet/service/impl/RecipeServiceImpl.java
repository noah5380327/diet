package org.app.diet.service.impl;

import cn.hutool.core.date.DateUtil;
import org.app.diet.entity.ExerciseRecordEntity;
import org.app.diet.entity.RecipeEntity;
import org.app.diet.entity.StudentProfileEntity;
import org.app.diet.exception.CoreException;
import org.app.diet.repository.ExerciseRecordRepository;
import org.app.diet.repository.RecipeRepository;
import org.app.diet.repository.StudentProfileRepository;
import org.app.diet.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private ExerciseRecordRepository exerciseRecordRepository;

    @Override
    public RecipeEntity generateRecipe() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Boolean exist = recipeRepository.existsByUserIdAndCreatedTimeBetween(
                userId,
                DateUtil.beginOfDay(DateUtil.date()),
                DateUtil.beginOfDay(DateUtil.tomorrow())
        );
        if (exist) {
            throw new CoreException("Your meal plan for today is ready. It cannot be regenerated.");
        }

        StudentProfileEntity profile = studentProfileRepository.findByUserId(userId);

        List<ExerciseRecordEntity> records = exerciseRecordRepository.findByUserIdAndCreatedTimeBetween(
                userId,
                DateUtil.beginOfDay(DateUtil.yesterday()),
                DateUtil.beginOfDay(DateUtil.date())
        );

        int burned = records.stream().mapToInt(ExerciseRecordEntity::getCalories).sum();
        int targetCalories;

        switch (profile.getGoal()) {
            case "LOSE_WEIGHT" -> targetCalories = burned - 500;
            case "GAIN_MUSCLE" -> targetCalories = burned + 300;
            default -> targetCalories = burned;
        }

        targetCalories = Math.max(1200, targetCalories);

        String breakfast = "Oatmeal + Egg + Spinach";
        String lunch = "Chicken Breast + Brown Rice + Broccoli";
        String dinner = "Beef + Sweet Potato + Tomato";

        RecipeEntity recipe = new RecipeEntity();
        recipe.setUserId(userId);
        recipe.setBreakfast(breakfast);
        recipe.setLunch(lunch);
        recipe.setDinner(dinner);
        recipe.setTotalCalories(targetCalories);
        recipeRepository.save(recipe);
        return recipe;
    }

    @Override
    public RecipeEntity getLatestRecipe() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return recipeRepository.findTopByUserIdOrderByCreatedTimeDesc(userId);
    }
}
