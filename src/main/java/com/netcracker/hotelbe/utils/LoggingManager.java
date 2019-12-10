package com.netcracker.hotelbe.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingManager {

    private Logger log = LogManager.getLogger(LoggingManager.class);

    @Pointcut("execution(* com.netcracker.hotelbe.*.*.*(..) )")
    public void allPointcut() {

    }

    @Around("allPointcut()")
    public Object logControllerMethods(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object object = pjp.proceed();
        if (className.contains("com.netcracker.hotelbe.controller")) {
            log.info(className + " : " + methodName + "() " + "Response : "
                    + new ObjectMapper().writeValueAsString(object));
        }
        if (className.contains("com.netcracker.hotelbe.service")) {
            log.trace(className + " : " + methodName + "() " + "Return : "
                    + new ObjectMapper().writeValueAsString(object));
        }

        return object;
    }

    @AfterThrowing(value = "allPointcut()", throwing = "e")
    public void logAllThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {
        Throwable rootCause = Throwables.getRootCause(e);
        if (rootCause instanceof PSQLException) {
            return;
        }
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
        log.error(className + " : " + methodName + "() " + " : "
                + new ObjectMapper().writeValueAsString(joinPoint.getArgs()), e);
    }

}
