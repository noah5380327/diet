package org.app.diet.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentProfileCreateVo {

    @NotNull(message = "age is required")
    @Min(value = 1, message = "age must be greater than 0")
    private Integer age;

    @NotNull(message = "initWeight is required")
    @DecimalMin(value = "0.1", message = "initWeight must be greater than 0")
    private Double initWeight;

    @NotNull(message = "targetWeight is required")
    @DecimalMin(value = "0.1", message = "targetWeight must be greater than 0")
    private Double targetWeight;

    @NotNull(message = "trainingDuration is required")
    @Min(value = 1, message = "trainingDuration must be greater than 0")
    private Integer trainingDuration;

    @NotBlank(message = "goal is required")
    private String goal;
}
