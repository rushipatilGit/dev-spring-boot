package com.luv2code.aopdemo.aspect;


import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


// @Component as it needs to discovered as component during component scanning.
@Aspect
@Component
@Order(2)
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
/*
    @Pointcut("execution (* com.luv2code.aopdemo.dao.*.*(..))") // creating a point cut declaration
    private void forDaoPackage() {}// name of pointcut declaration - can have any name

    // create a pointcut for getter methods
    @Pointcut("execution (* com.luv2code.aopdemo.dao.*.get*(..))")
    private void getter() {}
    //create a pointcut for setter methods
    @Pointcut("execution (* com.luv2code.aopdemo.dao.*.set*(..))")
    private void setter() {}
    //create a pointcut: include package ...and exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {}

 */

   // @Before("forDaoPackage()") // applying the pointcut declaration to the advice
  //  @Before("forDaoPackageNoGetterSetter()")
   @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=====>>> Executing @Before advise on method");

        // display the method signature
       MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
       System.out.println("Method: "+ methodSignature);

       // display method arguments
       //get args
       Object[] args = theJoinPoint.getArgs();
       // loop through args
       for(Object tempArg : args){
           System.out.println("Method arg :" + tempArg);
           if(tempArg instanceof Account){
                // downcast and print Account specific stuff
               Account theAccount = (Account) tempArg;
               System.out.println("Account Name :" + theAccount.getName());
               System.out.println("Account Level :" + theAccount.getLevel());
           }
       }
    }

    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{

       // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=======> Executing @Around on method :" + method);
       // get begin timestamp
        long begin = System.currentTimeMillis();

       //not. lets execute the method
        Object result;
        try {
             result = theProceedingJoinPoint.proceed();
        } catch (Exception exc) {
            // log the exception
            System.out.println("In Around :" + exc.getMessage());
            // give user a custom message - @Around advice is only handling the exception
          //  result = "Major Accident! But no worries, your private AOP helicopter is on the way!";
            throw exc;  // @Around advice will re-throw the exception back to calling program.
        }
        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long duration = end - begin;

        System.out.println("\n======> Duration: " + duration / 1000.0 + " seconds");

       return result;

    }


    // Will execute regardless if exception is thrown or not - act as finally block
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=======> Executing @After (finally) on method :" + method);

    }

    // add a new advice for @AfterReturing on the findAccounts method
    @AfterReturning(
                pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
                returning = "results")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> results){

        // print out which method we are advising on
        //MethodSignature theMethodSignature = (MethodSignature)theJoinPoint.getSignature();
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=======> Executing @AfterReturning on method :" + method);

        // print out the results of the method call
        System.out.println("\n=====> result is:" + results);

        // lets post-process the data ... lets modify it :)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(results);

        System.out.println("\n ====> result is: " + results);

    }

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint , Throwable theExc){

       // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=======> Executing @AfterThrowing on method :" + method);

        // log the exception
        System.out.println("\n=======> The Exception is:" + theExc);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

       //loop through the accounts
        for(Account tempAccount:result) {
            // get the upper version of account
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }

    /*
    // @Before("forDaoPackage()")
    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> Performing API analytics");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void logToCloudAsync(){
        System.out.println("\n=====>>> Logging to Cloud in async fashion");
    }

     */
}


