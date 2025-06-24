package org.app.diet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.app.diet.entity.StudentProfileEntity;
import org.app.diet.exception.CoreException;
import org.app.diet.repository.StudentProfileRepository;
import org.app.diet.service.StudentProfileService;
import org.app.diet.vo.StudentProfileCreateVo;
import org.app.diet.vo.StudentProfileUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public void create(StudentProfileCreateVo vo) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Boolean exist = studentProfileRepository.existsByUserId(userId);
        if (exist) {
            throw new CoreException("profile already exists");
        }

        StudentProfileEntity studentProfileEntity = new StudentProfileEntity();
        BeanUtil.copyProperties(vo, studentProfileEntity);
        studentProfileEntity.setUserId(userId);
        studentProfileRepository.save(studentProfileEntity);
    }

    @Override
    public StudentProfileEntity getCurrentStudentProfile() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return studentProfileRepository.findByUserId(userId);
    }

    @Override
    public void updateById(String id, StudentProfileUpdateVo vo) {
        StudentProfileEntity studentProfileEntity = studentProfileRepository.findById(id).get();
        BeanUtil.copyProperties(vo, studentProfileEntity);
        studentProfileRepository.save(studentProfileEntity);
    }
}
