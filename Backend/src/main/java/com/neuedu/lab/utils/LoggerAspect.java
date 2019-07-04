package com.neuedu.lab.utils;


import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class LoggerAspect {
    @Pointcut("execution (* com.neuedu.lab.controller..*.*(..))")
    public void executeService(){

    }
    @Before(value = "executeService()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("[A request start]");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        printInfo("URL : " + request.getRequestURL().toString());
        printInfo("HTTP_METHOD : " + request.getMethod());
        printInfo("IP : " + request.getRemoteAddr());
        printInfo("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        printInfo("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "executeService()",returning = "response")
    public void doAfterReturning(JSONObject response){
//        printInfo("[CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        printInfo("RESPONSE : " + response);
    }
    public void printInfo(String s){
        System.out.println("[INFO]:"+s);
    }


}
