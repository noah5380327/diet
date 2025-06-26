package org.app.diet.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseRecordCreateForStudentVo extends  ExerciseRecordCreateVo {

    @NotBlank(message = "studentId is required")
    private String studentId;
}
