package org.app.diet.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.app.diet.constant.CoreConstant;
import org.app.diet.dto.LoginPersistDto;
import org.app.diet.exception.TokenException;
import org.app.diet.service.TokenService;
import org.app.diet.util.ResponseUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private final TokenService tokenService;
    private final RedisTemplate<String, LoginPersistDto> redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService, RedisTemplate<String, LoginPersistDto> redisTemplate) {
        super(authenticationManager);
        this.tokenService = tokenService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws IOException, ServletException {
        try {
            Authentication authentication = this.getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            ResponseUtil.tokenException(e, response);
        }
    }

    private Authentication getAuthentication(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(CoreConstant.AUTHORIZATION_HEADER_KEY);

        if (StrUtil.isBlank(authorizationHeader) || !StrUtil.startWith(authorizationHeader, CoreConstant.AUTHORIZATION_HEADER_VALUE_PREFIX)) {
            throw new TokenException("header not has Authorization or Authorization incorrect, url: " + request.getRequestURI());
        }

        String token = authorizationHeader.substring(CoreConstant.AUTHORIZATION_HEADER_VALUE_PREFIX.length());
        String subject = tokenService.getSubject(token);

        if (ObjectUtil.isNull(subject)) {
            throw new TokenException("token get subject fail, url: " + request.getRequestURI());
        }

        LoginPersistDto loginPersistDto = redisTemplate.opsForValue().get(token);

        if (ObjectUtil.isNull(loginPersistDto)) {
            throw new TokenException("redis get subject fail, url: " + request.getRequestURI());
        }

        List<String> roles = loginPersistDto.getRoles();
        List<String> permissions = loginPersistDto.getPermissions();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (ObjectUtil.isNotNull(roles)) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }

        if (ObjectUtil.isNotNull(permissions)) {
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginPersistDto.getUserId(), null, authorities);
        usernamePasswordAuthenticationToken.setDetails(loginPersistDto);
        return usernamePasswordAuthenticationToken;
    }
}
