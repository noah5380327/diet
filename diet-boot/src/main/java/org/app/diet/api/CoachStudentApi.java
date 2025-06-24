package org.app.diet.api;

import jakarta.validation.constraints.NotBlank;
import org.app.diet.annotation.CoreApi;
import org.app.diet.dto.ApiDto;
import org.app.diet.entity.CoachStudentEntity;
import org.app.diet.service.CoachStudentService;
import org.app.diet.util.ApiUtil;
import org.app.diet.vo.CoachStudentCreateVo;
import org.app.diet.vo.CoachStudentUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CoreApi
public class CoachStudentApi {

    @Autowired
    private CoachStudentService coachStudentService;

    @PostMapping("/coachStudents")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto create(@Validated @RequestBody CoachStudentCreateVo vo) {
        coachStudentService.create(vo);
        return ApiUtil.success();
    }

    @GetMapping("/coachStudents/current")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto getCurrentCoachStudent() {
        CoachStudentEntity coachStudentEntity = coachStudentService.getCurrentCoachStudent();
        return ApiUtil.success(coachStudentEntity);
    }

    @PutMapping("/coachStudents/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto updateById(@NotBlank @PathVariable("id") String id, @Validated @RequestBody CoachStudentUpdateVo vo) {
        coachStudentService.updateById(id, vo);
        return ApiUtil.success();
    }

    @PutMapping("/coachStudents/{id}/status/{status}")
    public ApiDto updateById(@NotBlank @PathVariable("id") String id, @NotBlank @PathVariable("status") String status) {
        coachStudentService.updateStatusById(id, status);
        return ApiUtil.success();
    }
}
