package org.app.diet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiDto {

    private String code;

    private String message;

    private Object data = "";
}