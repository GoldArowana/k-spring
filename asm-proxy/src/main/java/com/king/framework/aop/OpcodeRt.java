package com.king.framework.aop;

import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.HashMap;
import java.util.Map;

/**
 * 八、根据不同的返回类型使用不同字节码指令的助手类
 */
public class OpcodeRt {

    public static Map<String, Integer> map = new HashMap<>();

    static {


        map.put("int", Opcodes.IRETURN);

        map.put("byte", Opcodes.IRETURN);

        map.put("short", Opcodes.IRETURN);

        map.put("long", Opcodes.LRETURN);

        map.put("boolean", Opcodes.IRETURN);

        map.put("char", Opcodes.IRETURN);

        map.put("float", Opcodes.FRETURN);

        map.put("double", Opcodes.DRETURN);

        map.put("void", Opcodes.RETURN);


    }

    public static int getOpcodes(String type) {

        if (map.containsKey(type)) {

            return map.get(type);

        } else {


            return Opcodes.ARETURN;
        }

    }

}