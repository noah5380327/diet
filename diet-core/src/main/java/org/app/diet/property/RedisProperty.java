package org.app.diet.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Validated
@Getter
@Setter
public class RedisProperty {

    private String host;

    private Integer port;

    private String username;

    private String password;

    private Long expiredTime;
}
