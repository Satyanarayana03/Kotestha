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

    @Before("execution(* com.example.service.AgentService.*(..))")
    public void AgentControllerLogger(JoinPoint jp) {
        log.info("the Agent service class is called : {}", jp.getSignature().getName());
    }

    @After("execution(* com.example.service.AgentService.*(..))")
    public void AgentControllerAfterLogger(JoinPoint jp) {
        log.info("execution Completed : " + jp.getSignature().getName());
    }
}
