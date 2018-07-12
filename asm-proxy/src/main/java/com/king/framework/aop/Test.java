package com.king.framework.aop;

import java.util.Date;

/**
 * 十、测试类   3】Test
 * <p>
 * 使用ASM实现动态代理，需要先学懂JVM虚拟机的字节码指令。
 * 在自己写字节码指令的时候，如果你忘记了某些代码的指令的实现，
 * 别忘记使用JDK的javap -c -v -private **.class。
 * 通过javap我们可以解决好多我们曾经感到疑惑的地方，
 * 比如为什么匿名内部类使用局部变量时这个局部变量不能变？
 * 为什么在字节码层面上不能直接将基本类型复制给Object类型？
 * synchronized字节码中如何表述的。。。。。。
 */
public class Test {

    public static void main(String[] args) {

        SayHello sayHello = new SayHello();

        AddLogic logic = new AddLogicImpl(sayHello);

        GenSubProxy genSubProxy = new GenSubProxy(logic);

        Object obj = genSubProxy.genSubProxy(SayHello.class);

        SayHello sh = (SayHello) obj;

        sh.hh((byte) 1, new byte[]{}, 1, 1f, 's', 1, 1, new int[][]{{12}}, "", new String[][]{{"sdf", "s"}}, new Date());

        sh.sayHello("sg", "srt", 234, new String[]{});

    }

}