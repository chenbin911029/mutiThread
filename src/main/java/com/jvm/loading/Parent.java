package com.jvm.loading;

/**
 * Created by chenbin on 2019\9\23 0023.
 */
public class Parent {
    public static int A = 1;
    static {
        A = 2;
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
