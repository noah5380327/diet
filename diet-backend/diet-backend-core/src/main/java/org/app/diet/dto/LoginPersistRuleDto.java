package org.app.diet.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginPersistRuleDto implements Serializable {

    private String code;

    private String field;

    private String expression;

    private String value;
}
