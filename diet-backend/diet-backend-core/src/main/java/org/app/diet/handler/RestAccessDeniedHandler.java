package org.app.diet.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.app.diet.util.ResponseUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

@Configuration
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.securityException(e, httpServletResponse);
    }
}
