package com.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Java方法区溢出
 * 借助cglib字节码技术。
 * spring,hibernate在对类增强时都会使用到cglib.
 * 类越多越需要大的方法区来保证动态的calss可以加载入内存。
 *
 *  -verbose:gc -XX:+PrintGCDetails -Xms20M -XX:MaxDirectMemorySize=10M
 * Created by chenbin on 2019\9\1 0002.
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        try {
            Unsafe unsafe = (Unsafe)unsafeField.get(null);
            while (true) {
                unsafe.allocateMemory(_1MB);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        /**
         * Exception in thread "main" java.lang.OutOfMemoryError
         * at sun.misc.Unsafe.allocateMemory(Native Method)
         * at DirectMemoryOOM.main(DirectMemoryOOM.java:26)
         *
         */
    }
}
