package org.app.diet.handler;

import org.app.diet.dto.LoginPersistDto;
import org.app.diet.dto.LoginBackDto;
import org.app.diet.dto.LoginSuccessDto;
import org.app.diet.property.TokenProperty;
import org.app.diet.service.TokenService;
import org.app.diet.service.LoginService;
import org.app.diet.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Lazy
    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenProperty tokenProperty;

    @Resource
    private RedisTemplate<String, LoginPersistDto> redisTemplate;

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
        LoginSuccessDto loginSuccessDto = loginService.loginSuccess((UserDetails)authentication.getPrincipal());
        LoginPersistDto loginPersistDto = loginSuccessDto.getPersist();
        LoginBackDto loginBackDto = loginSuccessDto.getBack();

        String subject = loginSuccessDto.getSubject();

        String token = tokenService.generate(subject);

        redisTemplate.opsForValue().set(token, loginPersistDto, tokenProperty.getExpiredTime(), TimeUnit.SECONDS);

        loginBackDto.setToken(token);

        ResponseUtil.success(loginBackDto, response);
    }
}
