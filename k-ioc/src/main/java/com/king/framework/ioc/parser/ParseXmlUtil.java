package com.king.framework.ioc.parser;

import com.king.framework.ioc.parser.label.Bean;
import com.king.framework.ioc.parser.label.Property;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取xml配置文件的类
 */
public class ParseXmlUtil {

    /**
     * 根据指定的路径读取配置xml文件
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Bean> getBeanConfig(String path) {
        Map<String, Bean> result = new HashMap<>();

        // 加载配置文件
        InputStream is = ParseXmlUtil.class.getResourceAsStream(path);

        // XPath语句，用于选取所有 bean元素
        String xpath = "//bean";

        List<Element> beanNodes;

        try {
            beanNodes = new SAXReader().read(is).selectNodes(xpath);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("加载配置文件出错");
        }

        // 遍历所有bean节点,并将信息封装到Bean对象中
        for (Element ele : beanNodes) {
            Bean bean = new Bean() {{
                setName(ele.attributeValue("name"));
                setClassName(ele.attributeValue("class"));
                String scope = ele.attributeValue("scope");
                // 如果指定了scope则设置，不然用默认的singleton
                if (scope != null && scope.trim().length() > 0) {
                    setScope(scope);
                }
            }};

            List<Element> propNodes = ele.elements("property");
            if (propNodes == null) continue;
            for (Element prop : propNodes) {
                bean.getProperties().add(new Property() {{
                    setName(prop.attributeValue("name"));
                    setValue(prop.attributeValue("value"));
                    setRef(prop.attributeValue("ref"));
                }});
            }

            result.put(bean.getName(), bean);
        }
        return result;
    }
}
