package org.app.diet.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "token")
@Validated
@Getter
@Setter
public class TokenProperty {

    private String secret;

    private Integer expiredTime;

    private String[] ignorePaths;

    private String loginUrl;

    private String logoutUrl;

    private String loginUserNameParameter;

    private String loginPasswordParameter;
}
