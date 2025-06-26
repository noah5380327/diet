package org.app.diet.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exercise_record")
@Schema(name = "ExerciseRecord")
@Getter
@Setter
public class ExerciseRecordEntity extends CoreEntity {

    @Schema(description = "name")
    private String name;

    @Schema(description = "duration")
    private Integer duration;

    @Schema(description = "calories")
    private Integer calories;

    @Schema(description = "userId")
    private String userId;
}
