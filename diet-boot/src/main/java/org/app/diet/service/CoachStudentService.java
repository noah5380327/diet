package org.app.diet.service;

import org.app.diet.entity.CoachStudentEntity;
import org.app.diet.vo.CoachStudentCreateVo;
import org.app.diet.vo.CoachStudentUpdateVo;

public interface CoachStudentService {

   void create(CoachStudentCreateVo vo);

   CoachStudentEntity getCurrentCoachStudent();

   void updateById(String id, CoachStudentUpdateVo vo);

   void updateStatusById(String id, String status);
}
