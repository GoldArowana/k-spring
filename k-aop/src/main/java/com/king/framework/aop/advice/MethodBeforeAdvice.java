package com.king.framework.aop.advice;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public abstract class MethodBeforeAdvice implements MethodInterceptor {

    /**
     * @param method 目标方法
     * @param args   目标方法所需的参数
     * @param target 目标对象
     */
    public abstract void before(Method method, Object[] args, Object target);

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        // 前置通知方法，在目标方法前执行
        before(method, args, obj);
        // 目标方法执行
        Object result = proxy == null ? method.invoke(obj, args) : proxy.invokeSuper(obj, args);
        return result;
    }

}
