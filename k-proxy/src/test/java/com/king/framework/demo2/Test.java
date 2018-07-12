package com.king.framework.demo2;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        IHello ihello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(),  //加载接口的类加载器
                new Class[]{IHello.class},      //一组接口
                new MyInvocationHandler(new Hello())); //自定义的InvocationHandler
        ihello.sayHello();
    }
}