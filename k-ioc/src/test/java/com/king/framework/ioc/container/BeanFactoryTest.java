package com.king.framework.ioc.container;

import com.king.framework.ioc.bean.Programmer;
import org.junit.Test;

public class BeanFactoryTest {
    @Test
    public void t() {
        BeanFactory factory = new BeanFactoryImpl("/ioc.xml");
        Programmer programmer = (Programmer) factory.getBean("Programmer");
        System.out.println(programmer);
    }
}
