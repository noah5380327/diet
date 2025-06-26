package org.app.diet.service;

import org.app.diet.entity.ExerciseRecordEntity;
import org.app.diet.vo.ExerciseRecordCreateForStudentVo;
import org.app.diet.vo.ExerciseRecordCreateVo;
import org.app.diet.vo.ExerciseRecordUpdateVo;

import java.util.List;

public interface ExerciseRecordService {

   List<ExerciseRecordEntity> findAllWithCurrentUser();

   void create(ExerciseRecordCreateVo vo);

   void updateById(String id, ExerciseRecordUpdateVo vo);

   void deleteById(String id);

   void createForStudent(ExerciseRecordCreateForStudentVo vo);
}
