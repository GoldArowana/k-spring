package com.king.framework.aop;

/**
 * 计算本地变量表的长度，long，double类型会占用两个slot
 *
 * @author may
 * 六、用于计算局部变量表的slot长度
 */
public class LocalLen {


    public static int len(Class<?>[] clzz) {

        int count = 0;

        for (Class<?> class1 : clzz) {

            String str = class1.getName();
            if (str.equals("long") || str.equals("double")) {

                count += 2;

            } else {

                count++;
            }
        }

        return count;
    }

}