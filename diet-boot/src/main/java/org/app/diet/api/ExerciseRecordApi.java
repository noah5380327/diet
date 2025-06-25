package org.app.diet.api;

import jakarta.validation.constraints.NotBlank;
import org.app.diet.annotation.CoreApi;
import org.app.diet.dto.ApiDto;
import org.app.diet.entity.ExerciseRecordEntity;
import org.app.diet.service.ExerciseRecordService;
import org.app.diet.util.ApiUtil;
import org.app.diet.vo.ExerciseRecordCreateForStudentVo;
import org.app.diet.vo.ExerciseRecordCreateVo;
import org.app.diet.vo.ExerciseRecordUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CoreApi
public class ExerciseRecordApi {

    @Autowired
    private ExerciseRecordService exerciseRecordService;

    @PostMapping("/exerciseRecords")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto create(@Validated @RequestBody ExerciseRecordCreateVo vo) {
        exerciseRecordService.create(vo);
        return ApiUtil.success();
    }

    @GetMapping("/exerciseRecords")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto findAllWithCurrentUser() {
        List<ExerciseRecordEntity> list = exerciseRecordService.findAllWithCurrentUser();
        return ApiUtil.success(list);
    }

    @PutMapping("/exerciseRecords/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto updateById(@NotBlank @PathVariable("id") String id, @Validated @RequestBody ExerciseRecordUpdateVo vo) {
        exerciseRecordService.updateById(id, vo);
        return ApiUtil.success();
    }

    @DeleteMapping("/exerciseRecords/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ApiDto deleteById(@NotBlank @PathVariable("id") String id) {
        exerciseRecordService.deleteById(id);
        return ApiUtil.success();
    }

    @PostMapping("/exerciseRecords/forStudents")
    @PreAuthorize("hasRole('COACH')")
    public ApiDto create(@Validated @RequestBody ExerciseRecordCreateForStudentVo vo) {
        exerciseRecordService.createForStudent(vo);
        return ApiUtil.success();
    }
}
