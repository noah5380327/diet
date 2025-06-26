package org.app.diet.service.impl;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.app.diet.property.TokenProperty;
import org.app.diet.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenProperty tokenProperty;

    @Override
    public String generate(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(DateUtil.offsetSecond(DateUtil.date(), tokenProperty.getExpiredTime()))
                .signWith(SignatureAlgorithm.HS512, tokenProperty.getSecret())
                .compact();
    }

    @Override
    public String getSubject(String token) {
        String subject;

        try {
            subject = Jwts.parser()
                    .setSigningKey(tokenProperty.getSecret())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }

        return subject;
    }
}
