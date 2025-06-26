package org.app.diet.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtil {

    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final String LOGIN_EXCEPTION_MESSAGE = "username or password is incorrect";
    private static final String LOCKED_EXCEPTION_MESSAGE = "user is locked";

    public static void tokenException(Exception e, HttpServletResponse response) throws IOException {
        StaticLog.error(e);

        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        PrintWriter out = response.getWriter();
        String result = JSONUtil.toJsonPrettyStr(ApiUtil.tokenException());
        out.append(result);
    }

    public static void securityException(Exception e, HttpServletResponse response) throws IOException {
        StaticLog.error(e);

        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        PrintWriter out = response.getWriter();
        String result = JSONUtil.toJsonPrettyStr(ApiUtil.securityException());
        out.append(result);
    }

    public static void loginException(Exception e, HttpServletResponse response) throws IOException {
        coreException(e, response, LOGIN_EXCEPTION_MESSAGE);
    }

    public static void lockedException(Exception e, HttpServletResponse response) throws IOException {
        coreException(e, response, LOCKED_EXCEPTION_MESSAGE);
    }

    public static void success(Object data, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(ApiUtil.success(data));
        out.append(result);
    }

    private static void coreException(Exception e, HttpServletResponse response, String exceptionMessage) throws IOException {
        StaticLog.error(e);

        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        PrintWriter out = response.getWriter();
        String result = JSONUtil.toJsonPrettyStr(ApiUtil.coreException(exceptionMessage));
        out.append(result);
    }
}
