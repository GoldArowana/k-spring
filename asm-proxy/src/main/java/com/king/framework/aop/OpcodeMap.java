package com.king.framework.aop;

import org.objectweb.asm.Opcodes;

import java.util.HashMap;
import java.util.Map;

/**
 * 七、根据不同类型使用不同字节码指令助手类
 */
public class OpcodeMap {

    public static Map<String, Integer> map = new HashMap<>();

    static {


        map.put("int", Opcodes.ILOAD);

        map.put("byte", Opcodes.ILOAD);

        map.put("short", Opcodes.ILOAD);

        map.put("long", Opcodes.LLOAD);

        map.put("boolean", Opcodes.ILOAD);

        map.put("char", Opcodes.ILOAD);

        map.put("float", Opcodes.FLOAD);

        map.put("double", Opcodes.DLOAD);


    }

    public static int getOpcodes(String type) {

        if (map.containsKey(type)) {

            return map.get(type);

        } else {


            return Opcodes.ALOAD;
        }

    }

}