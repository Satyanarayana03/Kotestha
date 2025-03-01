package com.example.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Aspect
@Component
public class LoggerAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);


    @Before("execution(* com.example.service.CustomerService.*(..))")
    public void CustomerControllerLogger(JoinPoint jp) {
        LOGGER.info("the Customer service class is called : {}", jp.getSignature().getName());
    }

    @Before("execution(* com.example.service.PolicyService.*(..))")
    public void PolicyControllerLogger(JoinPoint jp) {
        LOGGER.info("the Policy service class is called : " + jp.getSignature().getName());
    }

    @Before("execution(* com.example.service.ClaimService.*(..))")
    public void CLaimControllerLogger(JoinPoint jp) {
        LOGGER.info("the Customer service class is called : {}", jp.getSignature().getName());
    }

    @After("execution(* com.example.service.CustomerService.*(..))")
    public void CustomerControllerAfterLogger(JoinPoint jp) {
        LOGGER.info("execution Completed : " + jp.getSignature().getName());
       // System.out.println("i am the After annotation");
    }

    @After("execution(* com.example.service.PolicyService.*(..))")
    public void PolicyControllerAfterLogger(JoinPoint jp) {
        LOGGER.info("the Policy service class is called : " + jp.getSignature().getName());
    }

    @After("execution(* com.example.service.CLaimService.*(..))")
    public void CLaimControllerAfterLogger(JoinPoint jp) {
        LOGGER.info("the Policy service class is called : " + jp.getSignature().getName());
    }


}
