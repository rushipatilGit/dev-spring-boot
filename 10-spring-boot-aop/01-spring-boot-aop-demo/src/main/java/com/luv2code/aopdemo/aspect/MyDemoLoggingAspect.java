package com.luv2code.aopdemo.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// @Component as it needs to discovered as component during component scanning.
@Aspect
@Component
public class MyDemoLoggingAspect
{
    // this is where we add all of our related advices for logging

    // let's start with an @Before Advice

    // point cut expression
    // point cut is a predicate expression for where advice should be applied
   //  @Before("execution (public void addAccount())")
    // calling point cut on fully classified class name
   //  @Before("execution (public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
    //@Before("execution (public void add*())")
    //@Before("execution (* add*())")
    // @Before("execution (* add*(com.luv2code.aopdemo.Account))") // Single param match
   // @Before("execution (* add*(com.luv2code.aopdemo.Account, ..))") //match on Account and any number of parameter(arguments)
      //@Before("execution (* add*(..))") // you can match on any parameters
    // Let's update the pointcut expression with the package name, *->For any class name and  * for any method name
    @Before("execution (* com.luv2code.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvise(){

        System.out.println("\n=====>>> Executing @Before advise on addAccount()");
    }
}


