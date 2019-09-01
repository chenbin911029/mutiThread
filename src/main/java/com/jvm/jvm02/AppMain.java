package com.jvm.jvm02;

/**
 * Java方法区，Java堆，Java栈
 * Created by chenbin on 2019\8\30 0030.
 */
//运行时，jvm把appmian的信息放入方法区
public class AppMain {
    //mian方法本身放在方法区
    public static void main(String[] args) {
        //test1是引用，放在栈区里；simple是自定义对象，放在堆里面
        Sample test1 = new Sample("测试1");
        Sample test2 = new Sample("测试2");

        test1.printName();
        test2.printName();
    }
}
