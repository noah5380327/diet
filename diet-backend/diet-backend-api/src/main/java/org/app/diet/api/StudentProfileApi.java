package org.app.diet.api;

import jakarta.validation.constraints.NotBlank;
import org.app.diet.annotation.CoreApi;
import org.app.diet.dto.ApiDto;
import org.app.diet.entity.StudentProfileEntity;
import org.app.diet.service.StudentProfileService;
import org.app.diet.util.ApiUtil;
import org.app.diet.vo.StudentProfileCreateVo;
import org.app.diet.vo.StudentProfileUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CoreApi
public class StudentProfileApi {

    @Autowired
    private StudentProfileService studentProfileService;

    @PostMapping("/studentProfiles")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto create(@Validated @RequestBody StudentProfileCreateVo vo) {
        studentProfileService.create(vo);
        return ApiUtil.success();
    }

    @GetMapping("/studentProfiles/current")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto getCurrentStudentProfile() {
        StudentProfileEntity studentProfileEntity = studentProfileService.getCurrentStudentProfile();
        return ApiUtil.success(studentProfileEntity);
    }

    @PutMapping("/studentProfiles/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto updateById(@NotBlank @PathVariable("id") String id, @Validated @RequestBody StudentProfileUpdateVo vo) {
        studentProfileService.updateById(id, vo);
        return ApiUtil.success();
    }
}
