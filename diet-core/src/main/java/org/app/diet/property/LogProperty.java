package org.app.diet.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "log")
@Validated
@Getter
@Setter
public class LogProperty {

    private Boolean enabled;
}
