package com.jvm.overload;

/**
 * 重载方法解析
 * Created by chenbin on 2019\9\24 0024.
 */
public class StaticDispatch {
    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy.");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman.");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady.");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        //Human类型，会匹配到超类，而不会匹配到最精确类型
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

        Man man2 = new Man();
        Woman woman2 = new Woman();
        sr.sayHello(man2);
        sr.sayHello(woman2);
    }
}
