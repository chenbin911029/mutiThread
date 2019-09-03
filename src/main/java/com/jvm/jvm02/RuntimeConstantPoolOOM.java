package com.jvm.jvm02;

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
            list.add(String.valueOf(i++).intern());
        }
    }
}
