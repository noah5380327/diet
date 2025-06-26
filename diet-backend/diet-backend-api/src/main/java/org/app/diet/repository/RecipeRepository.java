package org.app.diet.repository;

import org.app.diet.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, String>, JpaSpecificationExecutor<RecipeEntity> {

    RecipeEntity findTopByUserIdOrderByCreatedTimeDesc(String userId);

    Boolean existsByUserIdAndCreatedTimeBetween(String studentId, Date startTime, Date endTime);
}
