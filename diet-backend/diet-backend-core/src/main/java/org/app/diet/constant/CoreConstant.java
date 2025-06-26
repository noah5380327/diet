package org.app.diet.constant;

public interface CoreConstant {

    String API_PREFIX = "/api";

    String AUTHORIZATION_HEADER_KEY = "Authorization";

    String AUTHORIZATION_HEADER_VALUE_PREFIX = "Bearer ";

    String[] DEFAULT_IGNORE_PATHS = new String[]{
            "/favicon.ico",
            "/static/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/",
            "/druid/**",
            API_PREFIX + "/captcha",
            API_PREFIX + "/gt/**",
    };
}
