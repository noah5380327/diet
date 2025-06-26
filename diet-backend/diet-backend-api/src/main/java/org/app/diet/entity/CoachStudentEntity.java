package org.app.diet.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coach_student")
@Schema(name = "CoachStudent")
@Getter
@Setter
public class CoachStudentEntity extends CoreEntity {

    @Schema(description = "studentId")
    private String studentId;

    @Schema(description = "coachId")
    private String coachId;

    @Schema(description = "status")
    private String status;
}
