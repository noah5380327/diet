package org.app.diet.service;

import org.app.diet.entity.StudentProfileEntity;
import org.app.diet.vo.StudentProfileCreateVo;
import org.app.diet.vo.StudentProfileUpdateVo;

public interface StudentProfileService {

   void create(StudentProfileCreateVo vo);

   StudentProfileEntity getCurrentStudentProfile();

   void updateById(String id, StudentProfileUpdateVo vo);
}
