package com.jvm.jvm02;

/**
 * 栈内存溢出
 * 该示例需要设置JVM参数：
 * -verbose:gc -XX:+PrintGCDetails -Xss2M
 *
 * Created by chenbin on 2019\9\1 0002.
 */
public class JavaVMStackOOM {

    public void doNotStop() {
        while (true) {

        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        while (true) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    oom.doNotStop();
                }
            });
            t.start();
        }
    }
}
