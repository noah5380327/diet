package org.app.diet.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class LoginPersistDto implements Serializable {

    private String userId;

    private List<String> roles;

    private List<String> permissions;

    private List<LoginPersistRuleDto> rules;
}
