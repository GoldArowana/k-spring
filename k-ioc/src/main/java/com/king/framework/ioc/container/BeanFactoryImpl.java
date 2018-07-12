package com.king.framework.ioc.container;

import com.king.framework.ioc.parser.ParseXmlUtil;
import com.king.framework.ioc.parser.label.Bean;
import com.king.framework.ioc.parser.label.Property;
import com.king.framework.proxy.ProxyFactoryBean;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactoryImpl implements BeanFactory {
    // 存放配置文件信息
    private Map<String, Bean> config;

    // 存放bean对象的容器
    private Map<String, Object> context = new HashMap<>();

    public BeanFactoryImpl(String path) {
        // 读取配置文件中bean的信息
        config = ParseXmlUtil.getBeanConfig(path);

        if (config == null) return;

        // 把SINGLETON的, 都放进context中
        config.forEach((beanName, bean) -> {
            if (bean.getScope().equals(Bean.SINGLETON)) {
                Object beanObj = createBeanByConfig(bean);
                context.put(beanName, beanObj);
            }
        });
    }

    @Override
    public Object getBean(String name) {
        // 根据name, 获取bean
        Bean bean = config.get(name);

        // 如果是单例的, 那么就在context中取
        return bean.getScope().equals(Bean.SINGLETON) ? context.get(name)
                // 如果不是单里的, 那么就重新构造一个, 然后返回.
                : createBeanByConfig(bean);
    }

    /**
     * 根据bean的配置信息创建一个新的bean对象
     */
    private Object createBeanByConfig(Bean bean) {
        try {
            // 获取bean的class
            Class clazz = Class.forName(bean.getClassName());

            // 根据class, new一个实例
            Object beanObj = clazz.newInstance();

            // 获取bean对象中的property配置
            List<Property> properties = bean.getProperties();

            // 遍历bean对象中的property配置,并将对应的value或者ref注入到bean对象中
            for (Property prop : properties) {
                if (prop.getValue() != null) {

                    // 将value值注入到bean对象中
                    BeanUtils.populate(beanObj, new HashMap<String, Object>() {{
                        put(prop.getName(), prop.getValue());
                    }});
                } else if (prop.getRef() != null) {
                    Object ref = context.get(prop.getRef());
                    Object newRef = (ref != null) ? ref : createBeanByConfig(config.get(prop.getRef()));

                    // 将ref对象注入bean对象中
                    BeanUtils.populate(beanObj, new HashMap<String, Object>() {{
                        put(prop.getName(), newRef);
                    }});
                }
            }

            // 说明是要创建代理对象
            if (clazz.equals(ProxyFactoryBean.class)) {
                ProxyFactoryBean factoryBean = (ProxyFactoryBean) beanObj;
                // 创建代理对象
                beanObj = factoryBean.createProxy();
            }

            return beanObj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("创建" + bean.getClassName() + "对象失败");
        }
    }
}
