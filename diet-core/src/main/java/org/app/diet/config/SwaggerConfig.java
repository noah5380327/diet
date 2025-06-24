package org.app.diet.config;

import org.app.diet.property.SwaggerProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Autowired
    private SwaggerProperty swaggerProperty;

    @Bean
    public OpenAPI customOpenAPI() {
        String securitySchemeName = "Token";

        return new OpenAPI()
                .info(new Info()
                        .title(swaggerProperty.getTitle())
                        .version(swaggerProperty.getVersion())
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                        )
                );
    }
}
