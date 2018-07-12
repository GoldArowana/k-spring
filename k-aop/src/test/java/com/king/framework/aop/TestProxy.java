package com.king.framework.aop;

import com.king.framework.aop.dao.UserDao;
import com.king.framework.ioc.container.BeanFactory;
import com.king.framework.ioc.container.BeanFactoryImpl;
import org.junit.Test;

public class TestProxy {

    @Test
    public void testJDKProxy() {
        BeanFactory ac = new BeanFactoryImpl("/aop.xml");
        UserDao userDao = (UserDao) ac.getBean("userDaoProxy");
        System.out.println(userDao.getClass());
        userDao.add("Jason");
        String user = userDao.getUser("132");
        System.out.println(user);
    }
}
