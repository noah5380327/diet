package org.app.diet.repository;

import org.app.diet.entity.StudentProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfileEntity, String>, JpaSpecificationExecutor<StudentProfileEntity> {

    Boolean existsByUserId(String userId);

    StudentProfileEntity findByUserId(String userId);
}
