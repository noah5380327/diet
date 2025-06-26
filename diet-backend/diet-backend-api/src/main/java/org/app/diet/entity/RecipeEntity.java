package org.app.diet.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "recipe")
@Schema(name = "Recipe")
@Getter
@Setter
public class RecipeEntity extends CoreEntity {

    @Schema(description = "breakfast")
    private String breakfast;

    @Schema(description = "lunch")
    private String lunch;

    @Schema(description = "dinner")
    private String dinner;

    @Schema(description = "totalCalories")
    private Integer totalCalories;

    @Schema(description = "userId")
    private String userId;
}
