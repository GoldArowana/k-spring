package com.king.framework.aop.advice.impl;

import com.king.framework.aop.advice.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MyBeforeAdvice extends MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("前置通知");
    }

}