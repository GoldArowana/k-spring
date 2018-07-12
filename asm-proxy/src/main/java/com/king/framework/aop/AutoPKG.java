package com.king.framework.aop;

import java.util.HashMap;
import java.util.Map;

/**
 * 三.如果方法参数中存在基本类型参数，要自动打包成Object[] args，写个基本类型对应包装类助手
 */
public class AutoPKG {

    public static final Map<String, String> map = new HashMap<>();

    static {
        map.put("int", "java/lang/Integer");

        map.put("byte", "java/lang/Byte");

        map.put("short", "java/lang/Short");

        map.put("long", "java/lang/Long");

        map.put("boolean", "java/lang/Boolean");

        map.put("char", "java/lang/Character");

        map.put("float", "java/lang/Float");

        map.put("double", "java/lang/Double");

    }

    public static String auto(String type) {

        if (map.containsKey(type)) {

            return map.get(type);
        } else {


            return null;
        }


    }

}