package com.king.framework.demo3;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

class SubClass implements MethodInterceptor {

    //代理类需要代理的方法
    @Override
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {

        System.out.println("MethodInterceptor start...");
        proxy.invokeSuper(obj, args);
        System.out.println("MethodInterceptor ending...");

        return null;
    }

}

//被代理的类
class SuperClass {

    public void hello(String name) {
        System.out.println("hello, " + name);
    }

    public void bye(String name) {
        System.out.println("bye, " + name);
    }
}

//过滤器-只对被代理类的bye()方法进行增强
class ProxyFilter implements CallbackFilter {

    @Override
    public int accept(Method arg0) {
        if ("bye".equalsIgnoreCase(arg0.getName())) {
            return 0;
        }
        return 1;
    }

}

public class GCLibTest {

    public static void main(String args[]) {

//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(SuperClass.class); //设置被代理的类
//        enhancer.setCallbacks(new Callback[]{new SubClass(), NoOp.INSTANCE}); //根据SubClass中的实现对方法进行增强
//        enhancer.setCallbackFilter(new ProxyFilter()); //使用过滤器
//        SuperClass create = (SuperClass) enhancer.create();
//
//        create.hello("ken");
//
//        //使用enhancer产生的代理类不需要修改SuperClass类中的代码，就可以对bye()进行增强
//        create.bye("ken");


        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SuperClass.class); //设置被代理的类
        enhancer.setCallbacks(new Callback[]{new SubClass(), NoOp.INSTANCE}); //根据SubClass中的实现对方法进行增强
        enhancer.setCallbackFilter(new ProxyFilter()); //使用过滤器
        Object obj = enhancer.create();
        SuperClass o = SuperClass.class.cast(obj);
        o.bye("nihao");
//        try {
//            Method m = obj.getClass().getDeclaredMethod("hello");
//            System.out.println(m.toString());
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        create.hello("ken");

        //使用enhancer产生的代理类不需要修改SuperClass类中的代码，就可以对bye()进行增强
//        create.bye("ken");
    }
}