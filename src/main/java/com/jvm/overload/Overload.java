package com.jvm.overload;

import java.io.Serializable;

/**
 * Created by chenbin on 2019\9\24 0024.
 */
public class Overload {
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void sayHello(char...chars) {
        System.out.println("hello chars...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        /**
         * char->int->long->float->double->Character->
         * Serializable->Object->char....
         */
        sayHello('a');
    }
}
