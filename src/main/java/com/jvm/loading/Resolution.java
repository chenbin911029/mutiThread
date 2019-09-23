package com.jvm.loading;

/**
 * Created by chenbin on 2019\9\23 0023.
 */
public class Resolution {
    static {
        i = 0;
        //这句编译会提示，非法向前引用
        //System.out.println(i);
    }

    static int i = 1;

    public static void main(String[] args) {
        System.out.println(i);
    }
}
