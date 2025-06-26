package org.app.diet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.app.diet.entity.ExerciseRecordEntity;
import org.app.diet.exception.CoreException;
import org.app.diet.repository.CoachStudentRepository;
import org.app.diet.repository.ExerciseRecordRepository;
import org.app.diet.service.ExerciseRecordService;
import org.app.diet.vo.ExerciseRecordCreateForStudentVo;
import org.app.diet.vo.ExerciseRecordCreateVo;
import org.app.diet.vo.ExerciseRecordUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseRecordServiceImpl implements ExerciseRecordService {

    @Autowired
    private ExerciseRecordRepository exerciseRecordRepository;

    @Autowired
    private CoachStudentRepository coachStudentRepository;

    @Override
    public List<ExerciseRecordEntity> findAllWithCurrentUser() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return exerciseRecordRepository.findByUserIdOrderByCreatedTimeDesc(userId);
    }

    @Override
    public void create(ExerciseRecordCreateVo vo) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Boolean exist = exerciseRecordRepository.existsByName(vo.getName());
        if (exist) {
            throw new CoreException("record already exists");
        }

        ExerciseRecordEntity exerciseRecordEntity = new ExerciseRecordEntity();
        BeanUtil.copyProperties(vo, exerciseRecordEntity);
        exerciseRecordEntity.setUserId(userId);
        exerciseRecordRepository.save(exerciseRecordEntity);
    }

    @Override
    public void updateById(String id, ExerciseRecordUpdateVo vo) {
        ExerciseRecordEntity exerciseRecordEntity = exerciseRecordRepository.findById(id).get();
        BeanUtil.copyProperties(vo, exerciseRecordEntity);
        exerciseRecordRepository.save(exerciseRecordEntity);
    }

    @Override
    public void deleteById(String id) {
        exerciseRecordRepository.deleteById(id);
    }

    @Override
    public void createForStudent(ExerciseRecordCreateForStudentVo vo) {
        String coachId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String studentId = vo.getStudentId();

        Boolean exist = coachStudentRepository.existsByStudentIdAndCoachIdAndStatus(studentId, coachId, "ACCEPTED");
        if (!exist) {
            throw new CoreException("You are not bound to this student.");
        }

        ExerciseRecordEntity exerciseRecordEntity = new ExerciseRecordEntity();
        BeanUtil.copyProperties(vo, exerciseRecordEntity);
        exerciseRecordEntity.setUserId(studentId);
        exerciseRecordRepository.save(exerciseRecordEntity);
    }
}
