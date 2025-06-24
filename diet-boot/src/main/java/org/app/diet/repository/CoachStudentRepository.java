package org.app.diet.repository;

import org.app.diet.entity.CoachStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachStudentRepository extends JpaRepository<CoachStudentEntity, String>, JpaSpecificationExecutor<CoachStudentEntity> {

    Boolean existsByStudentId(String studentId);

    CoachStudentEntity findByStudentId(String studentId);

    List<CoachStudentEntity> findByCoachIdAndStatus(String coachId, String status);
}
