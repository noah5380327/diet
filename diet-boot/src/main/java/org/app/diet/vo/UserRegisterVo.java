package org.app.diet.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegisterVo {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    @Length(min = 6, message = "password length is greater than 6")
    private String password;

    private Boolean isCoach;
}
