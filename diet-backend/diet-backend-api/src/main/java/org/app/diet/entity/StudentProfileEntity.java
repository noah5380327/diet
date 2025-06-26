package org.app.diet.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_profile")
@Schema(name = "StudentProfile")
@Getter
@Setter
public class StudentProfileEntity extends CoreEntity {

    @Schema(description = "age")
    private Integer age;

    @Schema(description = "initWeight")
    private Double initWeight;

    @Schema(description = "targetWeight")
    private Double targetWeight;

    @Schema(description = "trainingDuration")
    private Integer trainingDuration;

    @Schema(description = "goal")
    private String goal;

    @Schema(description = "userId")
    private String userId;
}
