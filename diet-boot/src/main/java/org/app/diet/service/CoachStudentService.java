package org.app.diet.service;

import org.app.diet.dto.CoachStudentDto;
import org.app.diet.entity.CoachStudentEntity;
import org.app.diet.vo.CoachStudentCreateVo;
import org.app.diet.vo.CoachStudentUpdateVo;

import java.util.List;

public interface CoachStudentService {

   void create(CoachStudentCreateVo vo);

   CoachStudentEntity getCurrentCoachStudent();

   void updateById(String id, CoachStudentUpdateVo vo);

   List<CoachStudentDto> getAllPendingCoachStudents();

   void updateStatusById(String id, String status);
}
