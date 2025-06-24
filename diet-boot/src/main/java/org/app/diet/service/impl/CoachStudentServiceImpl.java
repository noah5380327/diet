package org.app.diet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.app.diet.entity.CoachStudentEntity;
import org.app.diet.exception.CoreException;
import org.app.diet.repository.CoachStudentRepository;
import org.app.diet.service.CoachStudentService;
import org.app.diet.vo.CoachStudentCreateVo;
import org.app.diet.vo.CoachStudentUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CoachStudentServiceImpl implements CoachStudentService {

    @Autowired
    private CoachStudentRepository coachStudentRepository;

    @Override
    public void create(CoachStudentCreateVo vo) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Boolean exist = coachStudentRepository.existsByStudentId(userId);
        if (exist) {
            throw new CoreException("record already exists");
        }

        CoachStudentEntity coachStudentEntity = new CoachStudentEntity();
        BeanUtil.copyProperties(vo, coachStudentEntity);
        coachStudentEntity.setStudentId(userId);
        coachStudentEntity.setStatus("PENDING");
        coachStudentRepository.save(coachStudentEntity);
    }

    @Override
    public CoachStudentEntity getCurrentCoachStudent() {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return coachStudentRepository.findByStudentId(userId);
    }

    @Override
    public void updateById(String id, CoachStudentUpdateVo vo) {
        CoachStudentEntity coachStudentEntity = coachStudentRepository.findById(id).get();
        BeanUtil.copyProperties(vo, coachStudentEntity);
        coachStudentRepository.save(coachStudentEntity);
    }

    @Override
    public void updateStatusById(String id, String status) {
        CoachStudentEntity coachStudentEntity = coachStudentRepository.findById(id).get();
        coachStudentEntity.setStatus(status);
        coachStudentRepository.save(coachStudentEntity);
    }
}
