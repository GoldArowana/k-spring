package com.king.framework.ioc.parser;

import org.junit.Test;

import java.util.Map;

public class ParseXmlUtilTest {
    @Test
    public void t() {
        Map m = ParseXmlUtil.getBeanConfig("/ioc.xml");
        m.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }
}
