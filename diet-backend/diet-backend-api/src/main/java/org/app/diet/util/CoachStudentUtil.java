package org.app.diet.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import org.app.diet.dto.CoachStudentDto;
import org.app.diet.entity.CoachStudentEntity;
import org.app.diet.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoachStudentUtil {

    public static List<String> convertToStudentIdList(List<CoachStudentEntity> coachStudentEntityList) {
        List<String> studentIdList = new ArrayList<>();
        if (ObjectUtil.isNotNull(coachStudentEntityList)) {
            for(CoachStudentEntity coachStudentEntity : coachStudentEntityList) {
                studentIdList.add(coachStudentEntity.getStudentId());
            }
        }

        return studentIdList;
    }

    public static List<CoachStudentDto> convertToDtoList(List<CoachStudentEntity> coachStudentEntityList, List<UserEntity> studentList) {
        Map<String, UserEntity> studentMap = studentList.stream().collect(Collectors.toMap(UserEntity::getId, userEntity -> userEntity));

        List<CoachStudentDto> coachStudentDtoList = new ArrayList<>();
        if (ObjectUtil.isNotNull(coachStudentEntityList)) {
            for(CoachStudentEntity coachStudentEntity : coachStudentEntityList) {
                CoachStudentDto coachStudentDto = new CoachStudentDto();
                BeanUtil.copyProperties(coachStudentEntity, coachStudentDto);
                coachStudentDto.setStudent(studentMap.get(coachStudentEntity.getStudentId()));
                coachStudentDtoList.add(coachStudentDto);
            }
        }
        return coachStudentDtoList;
    }
}
