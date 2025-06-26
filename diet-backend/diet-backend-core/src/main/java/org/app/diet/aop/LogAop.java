package org.app.diet.aop;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import jakarta.servlet.http.HttpServletRequest;
import org.app.diet.property.LogProperty;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class LogAop {

    @Autowired
    private LogProperty logProperty;

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(org.app.diet.annotation.CoreLog)")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        if (logProperty.getEnabled()) {
            startTime.set(System.currentTimeMillis());

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();

                StringBuilder sb = new StringBuilder("log start");
                sb.append(" ").append(request.getRequestURI());
                sb.append(" ").append(request.getMethod());

                if (ArrayUtil.isNotEmpty(joinPoint.getArgs())) {
                    sb.append(" ").append(JSONUtil.toJsonPrettyStr(joinPoint.getArgs()));
                }

                StaticLog.info(sb.toString());
            }
        }
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void afterReturning(Object result) {
        if (logProperty.getEnabled()) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();

                StringBuilder sb = new StringBuilder("log finish");
                sb.append(" ").append(request.getRequestURI());
                sb.append(" ").append(request.getMethod());

                if (ObjectUtil.isNotNull(result)) {
                    if (result instanceof String) {
                        sb.append(" ").append(result);
                    } else {
                        sb.append(" ").append(JSONUtil.toJsonPrettyStr(result));
                    }
                }

                sb.append(" ").append(System.currentTimeMillis() - startTime.get()).append("ms");

                StaticLog.info(sb.toString());
            }
        }
    }

    @AfterThrowing(throwing = "e", pointcut = "log()")
    public void afterThrowing(Throwable e) {
        if (logProperty.getEnabled()) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();

                String sb = "log finish" + " " + request.getRequestURI() +
                        " " + request.getMethod() +
                        " has exception: " + e.getMessage() +
                        " " + (System.currentTimeMillis() - startTime.get()) + "ms";

                StaticLog.error(sb);
            }
        }
    }
}
