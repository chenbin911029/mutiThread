package com.jvm.oom;

/**
 * 栈内存溢出
 * 该示例需要设置JVM参数：
 * -verbose:gc -XX:+PrintGCDetails -Xss2M
 *
 * 请先保存电脑上的当前工作，文档。
 * 该程序运行会导致操作系统假死，cpu使用率几乎耗尽，
 * 因此执行这段代码有较大的风险。
 *
 * Java的线程是映射到操作系统的内核线程上的。
 * -Xss:设置每个线程的堆栈大小
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
