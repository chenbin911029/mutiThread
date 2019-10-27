package com.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出
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
         at sun.misc.Unsafe.allocateMemory(Native Method)
         at com.jvm.oom.DirectMemoryOOM.main(DirectMemoryOOM.java:23)
         *
         */
    }
}
