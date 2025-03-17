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

    @Before("execution(* com.example.service.CustomerService.*(..))")
    public void CustomerControllerLogger(JoinPoint jp) {
        log.info("the Customer service class is called : {}", jp.getSignature().getName());
    }

    @After("execution(* com.example.service.CustomerService.*(..))")
    public void CustomerControllerAfterLogger(JoinPoint jp) {
        log.info("execution Completed : " + jp.getSignature().getName());
    }



}
