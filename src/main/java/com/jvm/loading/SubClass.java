package com.jvm.loading;

/**
 * Created by chenbin on 2019\9\20 0020.
 */
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }

    public SubClass() {
        System.out.println("SubClass Constructor.");
    }

    public static void main(String[] args) {
        /**
         * 加载SubClass时，执行顺序
         * 1.父类静态代码块
         * 2.子类静态代码块
         * 静态代码块只会执行一次
         */
        //System.out.println(SubClass.value);
        /**
         * 加载SubClass时，执行顺序
         * 1.父类静态代码块
         * 2.子类静态代码块
         * 3.父类构造函数
         * 4.子类构造函数
         */
        SubClass subClass = new SubClass();
    }
}
