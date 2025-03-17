package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {

    @Before("execution(* com.example.service.ClaimService.*(..))")
    public void CLaimControllerLogger(JoinPoint jp) {
        log.info("the Claim service class is called : {}", jp.getSignature().getName());
    }

    @After("execution(* com.example.service.CLaimService.*(..))")
    public void CLaimControllerAfterLogger(JoinPoint jp) {
        log.info("execution Completed : " + jp.getSignature().getName());
    }
}
