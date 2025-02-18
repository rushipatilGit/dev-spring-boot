package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

// Its regular class only containing pointCut expressions
@Aspect // is optional if you use only @PointCut expressions - if using Advice as well then you can use @Aspect
public class LuvAopExpressions {

    @Pointcut("execution (* com.luv2code.aopdemo.dao.*.*(..))") // creating a point cut declaration
    public void forDaoPackage() {}// name of pointcut declaration - can have any name

    // create a pointcut for getter methods
    @Pointcut("execution (* com.luv2code.aopdemo.dao.*.get*(..))")
    public void getter() {}
    //create a pointcut for setter methods
    @Pointcut("execution (* com.luv2code.aopdemo.dao.*.set*(..))")
    public void setter() {}
    //create a pointcut: include package ...and exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
