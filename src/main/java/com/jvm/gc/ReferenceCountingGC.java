package com.jvm.gc;

/**
 * 引用计数算法
 * 被引用则加1，失效则减1。
 * 引用计数算法很难解决对象之间的循环引用问题。
 * objA.instance = objB;
 * objB.instance =objA;
 * 它们没有被其它对象引用，不会再被访问，但由于相互引用对方，
 * 导致它们的引用计数都不为0，于是GC无法回收它们。
 * Created by chenbin on 2019\9\4 0004.
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;
        //这里发生GC，objA和objB是否回收
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }
}
