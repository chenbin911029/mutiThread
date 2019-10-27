package com.jvm.oom;

/**
 * 栈溢出
 * 该示例需要设置JVM参数：
 * -verbose:gc -XX:+PrintGCDetails -Xss128k
 *
 * Created by chenbin on 2019\9\1 0002.
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}

/**
 * stack length:19210
 Exception in thread "main" java.lang.StackOverflowError
 at com.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 at com.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 at com.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 at com.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 at com.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 at com.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:15)
 */