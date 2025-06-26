package org.app.diet.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseRecordCreateVo {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "duration is required")
    @Min(value = 1, message = "duration must be greater than 0")
    private Integer duration;

    @NotNull(message = "calories is required")
    @Min(value = 1, message = "calories must be greater than 0")
    private Integer calories;
}
