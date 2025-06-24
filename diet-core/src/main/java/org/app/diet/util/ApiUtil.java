package org.app.diet.util;

import org.app.diet.dto.ApiDto;

public class ApiUtil {

    private static final String SUCCESS_CODE = "success";
    private static final String SUCCESS_MESSAGE = "request success";
    private static final String NOT_FOUND_EXCEPTION_CODE = "not_found_exception";
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "route not found";
    private static final String SYSTEM_EXCEPTION_CODE = "system_exception";
    private static final String SYSTEM_EXCEPTION_MESSAGE = "system exception";
    private static final String VALIDATE_EXCEPTION_CODE = "validate_exception";
    private static final String VALIDATE_EXCEPTION_MESSAGE = "validate exception";
    private static final String CORE_EXCEPTION_CODE = "core_exception";
    private static final String TOKEN_EXCEPTION_CODE = "token_exception";
    private static final String TOKEN_EXCEPTION_MESSAGE = "login timeout";
    private static final String SECURITY_EXCEPTION_CODE = "security_exception";
    private static final String SECURITY_EXCEPTION_MESSAGE = "system denied";

    public static ApiDto success() {
        ApiDto result = new ApiDto();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MESSAGE);
        return result;
    }

    public static ApiDto success(Object data) {
        return success(SUCCESS_MESSAGE, data);
    }

    public static ApiDto success(String message, Object data) {
        ApiDto result = new ApiDto();
        result.setCode(SUCCESS_CODE);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static ApiDto notFoundException() {
        ApiDto result = new ApiDto();
        result.setCode(NOT_FOUND_EXCEPTION_CODE);
        result.setMessage(NOT_FOUND_EXCEPTION_MESSAGE);
        return result;
    }

    public static ApiDto systemException() {
        ApiDto result = new ApiDto();
        result.setCode(SYSTEM_EXCEPTION_CODE);
        result.setMessage(SYSTEM_EXCEPTION_MESSAGE);
        return result;
    }

    public static ApiDto validateException() {
        ApiDto result = new ApiDto();
        result.setCode(VALIDATE_EXCEPTION_CODE);
        result.setMessage(VALIDATE_EXCEPTION_MESSAGE);
        return result;
    }

    public static ApiDto coreException(String message) {
        ApiDto result = new ApiDto();
        result.setCode(CORE_EXCEPTION_CODE);
        result.setMessage(message);
        return result;
    }

    public static ApiDto tokenException() {
        ApiDto result = new ApiDto();
        result.setCode(TOKEN_EXCEPTION_CODE);
        result.setMessage(TOKEN_EXCEPTION_MESSAGE);
        return result;
    }

    public static ApiDto securityException() {
        ApiDto result = new ApiDto();
        result.setCode(SECURITY_EXCEPTION_CODE);
        result.setMessage(SECURITY_EXCEPTION_MESSAGE);
        return result;
    }
}
