package org.app.diet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuccessDto {

    private LoginPersistDto persist;

    private String subject;

    private LoginBackDto back;
}
