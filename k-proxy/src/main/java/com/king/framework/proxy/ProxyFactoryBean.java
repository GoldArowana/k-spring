package com.king.framework.proxy;

import lombok.Setter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于生产代理对象的类
 */
@Setter
public class ProxyFactoryBean {
    // 目标对象
    private Object target;
    // 通知
    private MethodInterceptor interceptor;
    // 代理实现的接口
    private String proxyInterface;

    /**
     * 创建代理对象
     */
    public Object createProxy() {
        // 判断有没有指定proxyInterface，没有指定就用CGLib方式
        if (proxyInterface == null || proxyInterface.trim().length() == 0)
            return createCGLibProxy();
        // 使用JDK中的代理
        return createJDKProxy();
    }

    /**
     * JDK方式创建代理对象
     */
    private Object createJDKProxy() {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(proxyInterface);// 实现的接口
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(proxyInterface + "找不到，请注意填写正确");
        }

        // JDK方式生成的代理对象
        Object proxyInstance = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = interceptor.intercept(target, method, args, null);
//                        Object result = method.invoke(target,args);
                        return result;
                    }
                });
        return proxyInstance;
    }

    /**
     * CGLib方式创建代理对象
     */
    private Object createCGLibProxy() {
        Enhancer enhancer = new Enhancer();
        // 设置代理对象父类
        enhancer.setSuperclass(target.getClass());
        // 设置增强
        enhancer.setCallback(interceptor);
        return enhancer.create();// 创建代理对象
    }

}
