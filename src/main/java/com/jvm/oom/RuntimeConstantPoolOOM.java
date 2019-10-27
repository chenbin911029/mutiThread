package com.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出（jdk1.6版本及之前的版本才会生效）
 * 该示例需要设置JVM参数：
 * -verbose:gc -XX:+PrintGCDetails -XX:MaxPermSize=10M -XX:MaxPermSize=10M
 *
 * Jdk1.6及之前的版本，由于常量池分配在永久代，可以通过 -XX:PermSize 和 -XX:MaxPermSize限制方法区大小，从而限制常量池的大小。
 * jdk1.7开始，常量分配在Java堆中，这段代码会有堆溢出异常 -verbose:gc -Xms20M -Xmx20M  -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=6
 * Created by chenbin on 2019\9\2 0003.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //使用list保持着常量池引用，避免Full GC 回收常量池行为
        List<String> list = new ArrayList<>();
        //10MB的PerSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++));
        }
    }
}

/**
 * [Full GC (Ergonomics) Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
 Disconnected from the target VM, address: '127.0.0.1:59980', transport: 'socket'
 at java.lang.Integer.toString(Integer.java:403)
 [PSYoungGen: 8192K->0K(9216K)] [ParOldGen: 10062K->566K(10240K)] 18254K->566K(19456K), [Metaspace: 2900K->2900K(1056768K)], 0.0069864 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
 at java.lang.String.valueOf(String.java:3099)
 at com.jvm.oom.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:23)
 */

/**
 * java.lang.OutOfMemoryError: GC overhead limit exceeded ，
 * 超出了GC开销限制。这个是JDK6新添的错误类型。是发生在GC占用大量时间为释放很小空间的时候发生的，
 * 是一种保护机制。一般是因为堆太小，导致异常的原因：没有足够的内存。
 * Sun 官方对此的定义：超过98%的时间用来做GC并且回收了不到2%的堆内存时会抛出此异常。
 */
