package org.app.diet.config;

import cn.hutool.core.util.ObjectUtil;
import jakarta.annotation.Resource;
import org.app.diet.constant.CoreConstant;
import org.app.diet.dto.LoginPersistDto;
import org.app.diet.filter.TokenAuthenticationFilter;
import org.app.diet.property.TokenProperty;
import org.app.diet.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class CoreWebSecurityConfig {

    @Autowired
    private TokenProperty tokenProperty;

    @Autowired
    private TokenService tokenService;

    @Resource
    private RedisTemplate<String, LoginPersistDto> redisTemplate;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failHandler;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            for (String ignorePath : CoreConstant.DEFAULT_IGNORE_PATHS) {
                web.ignoring().requestMatchers(ignorePath);
            }

            String[] ignorePaths = tokenProperty.getIgnorePaths();
            if (ObjectUtil.isNotEmpty(ignorePaths)) {
                for (String ignorePath : ignorePaths) {
                    String url = CoreConstant.API_PREFIX + ignorePath;
                    web.ignoring().requestMatchers(url);
                }
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(CoreConstant.API_PREFIX + tokenProperty.getLoginUrl()).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl(CoreConstant.API_PREFIX + tokenProperty.getLoginUrl())
                        .usernameParameter(tokenProperty.getLoginUserNameParameter())
                        .passwordParameter(tokenProperty.getLoginPasswordParameter())
                        .successHandler(successHandler)
                        .failureHandler(failHandler)
                )
                .logout(logout -> logout
                        .logoutUrl(CoreConstant.API_PREFIX + tokenProperty.getLogoutUrl())
                        .permitAll()
                )
                .cors(cors -> {
                })
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .addFilter(new TokenAuthenticationFilter(authenticationManager, tokenService, redisTemplate))
                .build();
    }
}
