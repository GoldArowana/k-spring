package com.king.framework.ioc.container;

public interface BeanFactory {
    /**
     * 根据ioc.xml中的name返回相应的bean实例
     */
    Object getBean(String name);
}
