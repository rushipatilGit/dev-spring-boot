package com.luv2code.springboot.thymeleafdemo.aspect;

import jakarta.persistence.Column;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // setup logger

    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {}

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){

        // display the method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====> in @Before: calling method: " + theMethod);
        // display the arguments to the methods

        // get the arguments
        Object[] args = theJoinPoint.getArgs();
        // loop through and display args
        for(Object tempArg : args){
            myLogger.info("=====> arguments :" + tempArg);
        }

    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut="forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint theJoinPoint , Object theResult){

        // display the method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====> in @AfterReturning: from method: " + theMethod);

        // display data returned
        myLogger.info("======> result: " + theResult);
    }
}
