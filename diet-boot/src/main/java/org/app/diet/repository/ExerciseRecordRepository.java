package org.app.diet.repository;

import org.app.diet.entity.ExerciseRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecordEntity, String>, JpaSpecificationExecutor<ExerciseRecordEntity> {

    Boolean existsByName(String name);

    List<ExerciseRecordEntity> findByUserIdOrderByCreatedTimeDesc(String userId);

    List<ExerciseRecordEntity> findByUserIdAndCreatedTimeBetween(String userId, Date startTime, Date endTime);
}
