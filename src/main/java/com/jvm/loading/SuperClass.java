package com.jvm.loading;

/**
 * Created by chenbin on 2019\9\20 0020.
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }

    public SuperClass() {
        System.out.println("SuperClass constructor.");
    }

    public static int value = 123;
}
