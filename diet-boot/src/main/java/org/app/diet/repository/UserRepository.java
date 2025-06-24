package org.app.diet.repository;

import org.app.diet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    List<UserEntity> findAllByIdIn(List<String> ids);
}
