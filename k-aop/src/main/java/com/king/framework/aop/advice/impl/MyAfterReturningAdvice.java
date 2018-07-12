package com.king.framework.aop.advice.impl;

import com.king.framework.aop.advice.AfterReturningAdvice;

import java.lang.reflect.Method;

public class MyAfterReturningAdvice extends AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("后置通知---方法返回值-->" + returnValue);
    }

}
