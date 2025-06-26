package org.app.diet.dto;

import lombok.Getter;
import lombok.Setter;
import org.app.diet.entity.UserEntity;

@Getter
@Setter
public class LoginDto {

    private UserEntity user;

    private String role;
}
