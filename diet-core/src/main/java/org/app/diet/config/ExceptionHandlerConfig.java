package org.app.diet.config;

import cn.hutool.log.StaticLog;
import org.app.diet.dto.ApiDto;
import org.app.diet.exception.CoreException;
import org.app.diet.util.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiDto noHandlerFoundException(NoHandlerFoundException e) {
        StaticLog.error(e);

        return ApiUtil.notFoundException();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiDto exception(Exception e) {
        StaticLog.error(e);

        return ApiUtil.systemException();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class })
    public ApiDto validateException(Exception e) {
        StaticLog.error(e);

        return ApiUtil.validateException();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CoreException.class)
    public ApiDto coreException(CoreException e) {
        StaticLog.error(e);

        return ApiUtil.coreException(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ApiDto accessDeniedException(AccessDeniedException e) {
        StaticLog.error(e);

        return ApiUtil.securityException();
    }
}
