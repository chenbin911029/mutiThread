package com.jvm.oom;

/**
 * Created by chenbin on 2019\8\30 0030.
 */
public class Sample {
    //name引用放入栈区里，name对象放入到堆里
    private String name;

    public Sample(String name) {
        this.name = name;
    }
    public void printName() {
        System.out.println(name);
    }
}
