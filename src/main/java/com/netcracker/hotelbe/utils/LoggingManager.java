package com.netcracker.hotelbe.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingManager {

    private Logger log = LogManager.getLogger(LoggingManager.class);
    private static String EXCEPTION_MESSAGE = "Exception in method: % with arguments %s. Message : %s";

//    @Pointcut(value="execution(* com.netcracker.hotelbe.*.*.*(..) )")
    @Pointcut(value="execution(* com.netcracker.hotelbe.service.*.*(..) )")
    public void projectPointcut(){

    }

    @Around("projectPointcut()")
    public Object logAllMethods(ProceedingJoinPoint pjp) throws Throwable {

        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        log.info("method invoked " + className + " : " + methodName + "()" + "arguments : "
                + mapper.writeValueAsString(array));
        Object object = pjp.proceed();
        log.info(className + " : " + methodName + "() " + "Response : "
                + mapper.writeValueAsString(object));
        return object;
    }

    @AfterThrowing(value = "projectPointcut()", throwing = "e")
    public void logAllThrowing(JoinPoint joinPoint, Throwable e) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().toString();
//        Signature signature = joinPoint.getSignature();
//        String stuff = signature.toString();
        log.error(className + " : " + methodName + "() " + " : "
                + mapper.writeValueAsString(joinPoint.getArgs()), e);//        log.error(String.format(EXCEPTION_MESSAGE, stuff));
//        log.error(String.format(EXCEPTION_MESSAGE, stuff, mapper.writeValueAsString(joinPoint.getArgs()), e.getMessage()));

//        String methodName = signature.getName();
//        String stuff = signature.toString();
//        String arguments = Arrays.toString(joinPoint.getArgs());
//
//        log.error(String.format(EXCEPTION_MESSAGE, methodName, signature, arguments, stuff, e.getMessage()), e);
//
//        return e;
//        log.error("Exception in method: "
//                + methodName + " with arguments "
//                + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
//                + e.getMessage(), e);
    }

}
