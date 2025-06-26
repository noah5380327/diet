package org.app.diet.service;

public interface TokenService {

    String generate(String subject);

    String getSubject(String token);
}
